package edu.yan.gestaobiblioteca.service.implementations;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import edu.yan.gestaobiblioteca.dto.autor.AutorFiltroDto;
import edu.yan.gestaobiblioteca.dto.autor.AutorPseudonimoInsertDto;
import edu.yan.gestaobiblioteca.dto.autor.AutorPseudonimoUpdateDto;
import edu.yan.gestaobiblioteca.dto.autor.AutorVerdadeiroInsertDto;
import edu.yan.gestaobiblioteca.dto.autor.AutorVerdadeiroUpdateDto;
import edu.yan.gestaobiblioteca.dto.autor.VincularAutorVerdadeiroDto;
import edu.yan.gestaobiblioteca.exception.autor.AutorJaEhPseudonimoException;
import edu.yan.gestaobiblioteca.exception.autor.AutorNaoEhPseudonimoException;
import edu.yan.gestaobiblioteca.exception.autor.AutorNaoEhVerdadeiroException;
import edu.yan.gestaobiblioteca.exception.autor.AutorNaoEncontradoException;
import edu.yan.gestaobiblioteca.exception.autor.AutorNaoPodeSerPseudonimoDeSiMesmoException;
import edu.yan.gestaobiblioteca.exception.autor.PseudonimoJaNaoPossuiVinculosComAutorVerdadeiroException;
import edu.yan.gestaobiblioteca.exception.autor.PseudonimoJaVinculadoAUmAutorException;
import edu.yan.gestaobiblioteca.exception.autor.PseudonimoNaoPertenceAoAutorInformadoException;
import edu.yan.gestaobiblioteca.model.Autor;
import edu.yan.gestaobiblioteca.respository.AutorRepository;
import edu.yan.gestaobiblioteca.respository.specification.AutorSpecifications;
import edu.yan.gestaobiblioteca.service.interfaces.IAutorService;
import jakarta.transaction.Transactional;

@Service
public class AutorServiceImplementation implements IAutorService{
	
	AutorRepository autorRepository;
	
	public AutorServiceImplementation(AutorRepository autorRepository) {
		this.autorRepository = autorRepository;
	}
	
    private Autor montarAutorVerdadeiro(AutorVerdadeiroInsertDto dto) {
        Autor autor = new Autor();
        autor.setPrenome(dto.getPrenome());
        autor.setSobrenome(dto.getSobrenome());
        autor.setSufixo(dto.getSufixo());
        autor.setDataNascimento(dto.getDataNascimento());
        autor.setDataMorte(dto.getDataMorte());
        autor.setBiografia(dto.getBiografia());
        autor.setAnonimo(false);
        return autor;
    }

    private Autor montarPseudonimo(AutorPseudonimoInsertDto pseudonimoInsertDto, Autor autorVerdadeiro) {
        Autor pseudonimo = new Autor();
        pseudonimo.setPrenome(pseudonimoInsertDto.getPrenome());
        pseudonimo.setSobrenome(pseudonimoInsertDto.getSobrenome());
        pseudonimo.setSufixo(pseudonimoInsertDto.getSufixo());
        pseudonimo.setDataNascimento(pseudonimoInsertDto.getDataNascimento());
        pseudonimo.setDataMorte(pseudonimoInsertDto.getDataMorte());
        pseudonimo.setBiografia(pseudonimoInsertDto.getBiografia());
        pseudonimo.setAnonimo(true);
        pseudonimo.setAutorVerdadeiro(autorVerdadeiro);
        return pseudonimo;
    }
    
    private Autor montarPseudonimo(AutorPseudonimoUpdateDto pseudonimoUpdateDto, Autor autorVerdadeiro) {
        Autor pseudonimo = new Autor();
        pseudonimo.setPrenome(pseudonimoUpdateDto.getPrenome());
        pseudonimo.setSobrenome(pseudonimoUpdateDto.getSobrenome());
        pseudonimo.setSufixo(pseudonimoUpdateDto.getSufixo());
        pseudonimo.setDataNascimento(pseudonimoUpdateDto.getDataNascimento());
        pseudonimo.setDataMorte(pseudonimoUpdateDto.getDataMorte());
        pseudonimo.setBiografia(pseudonimoUpdateDto.getBiografia());
        pseudonimo.setAnonimo(true);
        pseudonimo.setAutorVerdadeiro(autorVerdadeiro);
        return pseudonimo;
    }
    
    private void atualizarCamposBasicos(Autor autor, AutorVerdadeiroUpdateDto dto) {
        autor.setPrenome(dto.getPrenome());
        autor.setSobrenome(dto.getSobrenome());
        autor.setSufixo(dto.getSufixo());
        autor.setDataNascimento(dto.getDataNascimento());
        autor.setDataMorte(dto.getDataMorte());
        autor.setBiografia(dto.getBiografia());
    }
    
    private void atualizarCamposBasicos(Autor autor, AutorPseudonimoUpdateDto dto) {
        autor.setPrenome(dto.getPrenome());
        autor.setSobrenome(dto.getSobrenome());
        autor.setSufixo(dto.getSufixo());
        autor.setDataNascimento(dto.getDataNascimento());
        autor.setDataMorte(dto.getDataMorte());
        autor.setBiografia(dto.getBiografia());
    }
    
    private void sincronizarPseudonimos(Autor autorVerdadeiro, List<AutorPseudonimoUpdateDto> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return; // nada informado -> não altera os pseudônimos atuais
        }
    	List<Autor> pseudonimosAtuais = autorVerdadeiro.getPseudonimos();

        // indexa os pseudônimos atuais por id, pra buscar rápido
        Map<Long, Autor> pseudonimosPorId = pseudonimosAtuais.stream()
                .collect(Collectors.toMap(Autor::getId, Function.identity()));

        Set<Long> idsRecebidos = new HashSet<>();

        for (AutorPseudonimoUpdateDto pseudoDto : dtos) {
            if (pseudoDto.getId() == null) {
                // novo pseudônimo
                Autor novo = montarPseudonimo(pseudoDto, autorVerdadeiro);
                pseudonimosAtuais.add(novo);
            } else {
                // atualização de pseudônimo existente
                Autor existente = pseudonimosPorId.get(pseudoDto.getId());
                if (existente == null) {
                    throw new PseudonimoNaoPertenceAoAutorInformadoException(
                            "Pseudônimo com id " + pseudoDto.getId()
                            + " não pertence ao autor informado.");
                }
                atualizarCamposPseudonimo(existente, pseudoDto);
                idsRecebidos.add(pseudoDto.getId());
            }
        }

        // remove (orphanRemoval cuida do delete) os que não vieram na lista
        pseudonimosAtuais.removeIf(p -> p.getId() != null && !idsRecebidos.contains(p.getId()));
    }
    
    private void atualizarCamposPseudonimo(Autor pseudonimo, AutorPseudonimoUpdateDto dto) {
        pseudonimo.setPrenome(dto.getPrenome());
        pseudonimo.setSobrenome(dto.getSobrenome());
        pseudonimo.setSufixo(dto.getSufixo());
        pseudonimo.setDataNascimento(dto.getDataNascimento());
        pseudonimo.setDataMorte(dto.getDataMorte());
        pseudonimo.setBiografia(dto.getBiografia());
    }
	
	@Override
	@Transactional
	public Autor inserirAutorVerdadeiro(AutorVerdadeiroInsertDto autorDto) {
		Autor autorVerdadeiroBd = montarAutorVerdadeiro(autorDto);
		autorVerdadeiroBd = autorRepository.save(autorVerdadeiroBd);
		
        if (autorDto.getPseudonimos() != null) {
            for (AutorPseudonimoInsertDto pseudoDto : autorDto.getPseudonimos()) {
                Autor pseudonimo = montarPseudonimo(pseudoDto, autorVerdadeiroBd);
                autorRepository.save(pseudonimo);
            }
        }
		
     // recarrega com os pseudônimos já vinculados, evitando lazy loading na resposta
        return autorRepository.findByIdComPseudonimos(autorVerdadeiroBd.getId())
                .orElseThrow(() -> new AutorNaoEncontradoException(
                        "Autor não encontrado logo após o cadastro (inconsistência inesperada)"));
	}

	@Override
	public Autor inserirAutorPseudonimo(Long autorVerdadeiroId, AutorPseudonimoInsertDto autorPseudonimoDto) {
		Autor autorVerdadeiro = autorRepository.findById(autorVerdadeiroId)
				.orElseThrow(() -> new AutorNaoEncontradoException(
                        "Autor com id " + autorVerdadeiroId + " não encontrado"));
		
		// regra de negócio: impede encadeamento de pseudônimo -> pseudônimo
        if (autorVerdadeiro.getAutorVerdadeiro() != null) {
            throw new AutorJaEhPseudonimoException(
                    "O autor informado já é um pseudônimo. Vincule o novo pseudônimo ao autor verdadeiro raiz.");
        }
		
        Autor pseudonimo = montarPseudonimo(autorPseudonimoDto, autorVerdadeiro);
        return autorRepository.save(pseudonimo);
	}
	
	@Override
	public Autor inserirAutorPseudonimo(AutorPseudonimoInsertDto autorPseudonimoDto) {
	    Autor pseudonimo = montarPseudonimo(autorPseudonimoDto, null);

	    return autorRepository.save(pseudonimo);
	}
	
	@Transactional
	@Override
	public Autor atualizarAutorVerdadeiro(Long id, AutorVerdadeiroUpdateDto dto) {
	    Autor autor = autorRepository.findByIdComPseudonimos(id)
	            .orElseThrow(() -> new AutorNaoEncontradoException(
	                    "Autor com id " + id + " não encontrado"));

	    if (autor.getAutorVerdadeiro() != null) {
	        throw new AutorNaoEhVerdadeiroException(
	                "O autor informado é um pseudônimo e não pode ser atualizado por este endpoint.");
	    }

	    atualizarCamposBasicos(autor, dto);
	    sincronizarPseudonimos(autor, dto.getPseudonimos());

	    return autorRepository.save(autor);
	}
	
	@Transactional
	@Override
	public Autor atualizarAutorPseudonimo(Long id, AutorPseudonimoUpdateDto dto) {
		Autor autor = autorRepository.findByIdComAutorVerdadeiro(id).
				orElseThrow(() -> new AutorNaoEncontradoException(
						"Autor de id "+id+" não encontrado"));
		
		if(!autor.isAnonimo()) {
			throw new AutorNaoEhPseudonimoException("Autor de id "+id+" não é pseudonimo");
		}
		
		atualizarCamposBasicos(autor, dto);
		return autorRepository.save(autor);
	}
	
	@Override
	@Transactional
	public Autor vincularAutorVerdadeiro(Long pseudonimoId, Long autorVerdadeiroId) {

	    if (pseudonimoId.equals(autorVerdadeiroId)) {
	        throw new AutorNaoPodeSerPseudonimoDeSiMesmoException("Um autor não pode ser pseudônimo de si mesmo.");
	    }

	    Autor pseudonimo = autorRepository.findByIdComPseudonimos(pseudonimoId)
	            .orElseThrow(() -> new AutorNaoEncontradoException(
	                    "Autor com id " + pseudonimoId + " não encontrado"));

	    Autor autorVerdadeiro = autorRepository.findById(autorVerdadeiroId)
	            .orElseThrow(() -> new AutorNaoEncontradoException(
	                    "Autor com id " + autorVerdadeiroId + " não encontrado"));

	    if (pseudonimo.getAutorVerdadeiro() != null) {
	        throw new PseudonimoJaVinculadoAUmAutorException(
	                "Este autor já está vinculado a outro autor verdadeiro. Desvincule antes de criar um novo vínculo.");
	    }

	    if (autorVerdadeiro.getAutorVerdadeiro() != null) {
	        throw new AutorJaEhPseudonimoException(
	                "O autor de destino já é um pseudônimo. Vincule ao autor verdadeiro raiz.");
	    }

	    if (!pseudonimo.getPseudonimos().isEmpty()) {
	        throw new AutorNaoEhPseudonimoException(
	                "Este autor já possui pseudônimos próprios e não pode se tornar pseudônimo de outro autor.");
	    }

	    pseudonimo.setAutorVerdadeiro(autorVerdadeiro);
	    return autorRepository.save(pseudonimo);
	}

	@Override
	@Transactional
	public Autor desvincularAutorVerdadeiro(Long pseudonimoId) {
	    Autor pseudonimo = autorRepository.findById(pseudonimoId)
	            .orElseThrow(() -> new AutorNaoEncontradoException(
	                    "Autor com id " + pseudonimoId + " não encontrado"));

	    if (pseudonimo.getAutorVerdadeiro() == null) {
	        throw new PseudonimoJaNaoPossuiVinculosComAutorVerdadeiroException("Este autor já não possui vínculo com nenhum autor verdadeiro.");
	    }

	    pseudonimo.setAutorVerdadeiro(null);
	    return autorRepository.save(pseudonimo);
	}
		
	@Override
	public Iterable<Autor> buscarPorFiltro(AutorFiltroDto filtro) {

	    Specification<Autor> spec = Specification.where(null);
	    //nome do autor
	    if (filtro.getNome() != null) {
	        spec = spec.and(
	            AutorSpecifications.nomeContem(filtro.getNome())
	        );
	    }
	    //autor anonimo ou não
	    if (filtro.isAnonimo() == true) {
	        spec = spec.and(
	            AutorSpecifications.anonimo(filtro.isAnonimo())
	        );
	    }
	    
	    //adicionar a pesquisa  por dia, mes e ano de nascimento
	    if (filtro.getDataNascimento() != null) {
	        spec = spec.and(
	            AutorSpecifications.dataNascimento(
	                filtro.getDataNascimento()
	            )
	        );
	    }
	    
	    if (filtro.getDataNascimentoInicio() != null && filtro.getDataNascimentoFim() != null) {
            spec = spec.and(
                AutorSpecifications.dataNascimentoEntre(
                    filtro.getDataNascimentoInicio(),
                    filtro.getDataNascimentoFim()
                )
            );
        }
	    
	    if (filtro.getNasceuAntes() != null) {
	        spec = spec.and(
	            AutorSpecifications.nasceuAntes(
	                filtro.getNasceuAntes()
	            )
	        );
	    }

	    if (filtro.getNasceuApos() != null) {
	        spec = spec.and(
	            AutorSpecifications.nasceuApos(
	                filtro.getNasceuApos()
	            )
	        );
	    }
	    
    	//adicionar a pesquisa  por intervalo de tempo de morte
	    if (filtro.getDataMorte() != null) {
	        spec = spec.and(
	            AutorSpecifications.dataMorte(
	                filtro.getDataMorte()
	            )
	        );
	    }

	    if (filtro.getDataMorteInicio() != null &&
	        filtro.getDataMorteFim() != null) {

	        spec = spec.and(
	            AutorSpecifications.dataMorteEntre(
	                filtro.getDataMorteInicio(),
	                filtro.getDataMorteFim()
	            )
	        );
	    }
	    
	    //VIVO
	    if (Boolean.TRUE.equals(filtro.isVivo())) {
	        spec = spec.and(
	            AutorSpecifications.vivos()
	        );
	    }
	    
	    //MORTO
	    if (Boolean.TRUE.equals(filtro.isMorto())) {
	        spec = spec.and(
	            AutorSpecifications.mortos()
	        );
	    }
	    
	    return autorRepository.findAll(spec);
	}

	@Override
	public Optional<Autor> buscarAutorPorId(Long id) {
		// TODO Auto-generated method stub
		return autorRepository.findById(id);
	}
}
