# api-gestao-biblioteca
API REST para gestão de biblioteca, desenvolvida para gerenciar o cadastro de livros, autores, editoras, usuários e o controle de empréstimos. O sistema permite que funcionários registrem empréstimos e devoluções, enquanto clientes podem consultar o catálogo e agendar retiradas de livros.

## 1. LEVANTAMENTO DE REQUISITOS

### 1.2 Levantamento de Requisitos funcionais
- O sistema deve permitir que um cliente crie uma conta.
- O sistema deve permitir que o usuário realize login com e-mail e senha.
- O sistema deve permitir que o bibliotecário/admin registre empréstimos de livros para clientes.
- O sistema deve permitir que o cliente/bibliotecário/admin vizualize os livros disponíveis.
- O sistema deve permitir que o cliente/bibliotecário/admin vizualize os livros disponíveis.
- O sistema deve permitir que o cliente/bibliotecário/admin encontre livros por meio de data de publicação, autor, editora, ou ISBN.
- O sistema deve permitir que o bibliotecário/administrador cadastre/atualize/inative um livro.
- Nenhum dado é efetivamente excluido do sistema SGBD, isso vale para qualquer tabela, um item de uma tabela só é marcado como inativo, mas nunca é de fato removido (todas as tabelas precisam ter um campo chamado ativo então).
- O sistema deve permitir que o administrador cadastre/atualize/inative um bibliotecário.
- O sistema deve permitir cadastrar novos usuários com as credenciais usadas em contas já apagadas
- Nesse sistema específico, os CPF e o email não podem ser unique, uma vez que eles podeme estar em uma conta apagada
//continuar

### 1.3 Levantamento de Requisitos não funcionais
- O sistema deve fornecer segurança ao garantir autenticação em funções que envolvem exposição ou alteração de dados sensíveis.
- O sistema deve armazenar as senhas cadastradas pelos usuários usando um método de criptografia de senhas
  
### 1.2 LEVANTAMENTO DE REGRAS DE NEGÓCIO
| ID    | Regra de Negócio                | Descrição                                                                                                                                              | Condições                                                                                                                   |
| ----- | ------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------ | --------------------------------------------------------------------------------------------------------------------------- |
| RN01   | Empréstimo de Livro             | Um bibliotecário pode efetuar um empréstimo para um cliente.                                                                                           | Um livro possui 1 ou mais exemplares, sendo que o exemplar deve ser alugado, e não o livro.                                                                  |
| RN01.1 | Empréstimo com Reserva          | Caso o cliente tenha reservado o livro, o empréstimo é efetuado com sucesso se a posição da reserva do cliente for válida.                             | Número da reserva do cliente < quantidade de reservas disponíveis **e** número de reservas > 0.                             |
| RN01.2 | Empréstimo sem Reserva          | Caso o cliente não tenha reservado o livro, mas deseja alugar imediatamente, o empréstimo pode ser efetuado se determinadas condições forem atendidas. | Reservas disponíveis > 0 **e** livros alugados pelo cliente < limite máximo de empréstimos **e** cliente não está suspenso. |
| RN02   | Reserva de Livro                | Um cliente pode reservar um livro.                                                                                                                     | Livros alugados pelo cliente < limite máximo permitido **e** cliente não está em período de suspensão.                      |
| RN03   | Atualização da Fila de Reservas | Quando um empréstimo previamente reservado é iniciado, a fila de reservas deve ser atualizada.                                                         | O número de reserva de todos os outros clientes deve ser decrementado.                                                      |
| RN04   | Expiração de Reserva            | Uma reserva deve ser encerrada caso o empréstimo não seja iniciado dentro de um período definido.                                                      | Se o empréstimo não for efetuado em **X dias** definidos pelo campo `tempoExpiracaoAgendamento` em **Regras**.              |
| RN05   | Restrição de Role               | Um usuário pode possuir apenas uma role no sistema.                                                                                                    | Role única entre: **CLIENTE**, **BIBLIOTECARIO**, **ADMIN**.                                                                |
| RN06   | Cadastro de usuários clientes no sistema            | Um cliente só pode criar uma conta se o CPF inserido for **válido** e o CPF e o email não estiverem cadastrados na conta de um outro usuário ativo no BD.                                                                                           | Caso o CPF seja inválido, o sistema deve gerar uma exceção de `CPF inválido`; caso o email já esteja em uso em outra conta ativa (que não foi soft-deletado do sistema), o sistema deve lançar uma exceção de `usuário já cadastrado`; caso o CPF já esteja em uso em outra conta ativa (que não foi soft-deletado do sistema), o sistema deve lançar uma exceção de `CPF já cadastrado`. Após passar por essas 3 verificações, a conta pode ser criada com sucesso. 
| RN07   | Inserção do primeiro admin      | O sistema deve fazer o cadastro de um usuário de role admin automaticamente no sistema.                                                                                           | Toda vez que o sistema iniciar, ele deve verificar se há algum usuário admin ativo. Se não houver, ele deve criar um usuário com as credenciais padrões de admin e com o CPF '000.000.000-00', email 'admin-email@email.com', senha '#!segredo-admin-gestao-biblio!#' (por que na primeira vez que o sistema for executado o admin precisará logar de alguma forma, e o CPF, email e senha devem ser conhecidos).
| RN08   | Soft-delete de usuários      | O sistema deve manter o histórico e rastreabilidade dos empréstimos feitos mesmo após a remoção de um usuário.                                                                                           | Quando um usuário for removido, ele precisa ser mantido no banco de dados, para isso, a remoção de um usuário é um update que adiciona um valor ao campo  DATETIME `deleted-at` da tabela de usuários do BD, que por padrão é null. Para que ele possa ser removido, o campo `deleted-at` deve ter o valor nulo, caso ele já tenha uma data vinculada, o sistema deve lançar uma exceção `Usuário já apagado ` ao tentar apagar.



##2. DESCRICAO DOS CASOS DE USO

##3. ?
