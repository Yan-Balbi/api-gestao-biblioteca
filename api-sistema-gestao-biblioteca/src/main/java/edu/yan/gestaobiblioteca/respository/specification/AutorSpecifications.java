package edu.yan.gestaobiblioteca.respository.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import edu.yan.gestaobiblioteca.model.Autor;

public class AutorSpecifications {

    public static Specification<Autor> nomeContem(String nome) {
        return (root, query, cb) -> {

            String filtro = "%" + nome.toLowerCase() + "%";

            return cb.or(
                cb.like(cb.lower(root.get("prenome")), filtro),
                cb.like(cb.lower(root.get("sobrenome")), filtro),
                cb.like(cb.lower(root.get("sufixo")), filtro)
            );
        };
    }

    public static Specification<Autor> anonimo(Boolean anonimo) {
        return (root, query, cb) ->
            cb.equal(root.get("anonimo"), anonimo);
    }
    
    public static Specification<Autor> dataNascimento(LocalDate data) {
        return (root, query, cb) ->
            cb.equal(root.get("dataNascimento"), data);
    }
    
    public static Specification<Autor> dataNascimentoEntre(
            LocalDate inicio,
            LocalDate fim) {

        return (root, query, cb) ->
            cb.between(root.get("dataNascimento"), inicio, fim);
    }
    
    public static Specification<Autor> nasceuApos(LocalDate data) {
        return (root, query, cb) ->
            cb.greaterThanOrEqualTo(
                root.get("dataNascimento"),
                data
            );
    }

    public static Specification<Autor> nasceuAntes(LocalDate data) {
        return (root, query, cb) ->
            cb.lessThanOrEqualTo(
                root.get("dataNascimento"),
                data
            );
    }
    
    public static Specification<Autor> dataMorte(LocalDate data) {
        return (root, query, cb) ->
            cb.equal(root.get("dataMorte"), data);
    }
    
    public static Specification<Autor> dataMorteEntre(
            LocalDate inicio,
            LocalDate fim) {

        return (root, query, cb) ->
            cb.between(root.get("dataMorte"), inicio, fim);
    }
    
    public static Specification<Autor> morreuApos(LocalDate data) {
        return (root, query, cb) ->
            cb.greaterThanOrEqualTo(
                root.get("dataNascimento"),
                data
            );
    }

    public static Specification<Autor> morreuAntes(LocalDate data) {
        return (root, query, cb) ->
            cb.lessThanOrEqualTo(
                root.get("dataNascimento"),
                data
            );
    }
    
    public static Specification<Autor> vivos() {
        return (root, query, cb) ->
            cb.isNull(root.get("dataMorte"));
    }
    
    public static Specification<Autor> mortos() {
        return (root, query, cb) ->
            cb.isNotNull(root.get("dataMorte"));
    }
}
