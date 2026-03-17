# api-gestao-biblioteca
API REST para gestão de biblioteca, desenvolvida para gerenciar o cadastro de livros, autores, editoras, usuários e o controle de empréstimos. O sistema permite que funcionários registrem empréstimos e devoluções, enquanto clientes podem consultar o catálogo e agendar retiradas de livros.

##1. LEVANTAMENTO DE REQUISITOS

###1.2 Levantamento de Requisitos funcionais
- O sistema deve permitir que o usuário realize login com e-mail e senha.
- O sistema deve registrar empréstimos de livros para clientes.
- O sistema deve permitir que o cliente vizualize os livros disponíveis.
- O sistema deve permitir que o cliente vizualize os livros disponíveis.
- O sistema deve permitir que o cliente encontre livros por meio de data de publicação, autor, editora, ou ISBN.
//continuar
O sistema deve registrar empréstimos de livros para clientes.
###1.3 Levantamento de Requisitos não funcionais

###1.2 LEVANTAMENTO DE REGRAS DE NEGÓCIO
| ID    | Regra de Negócio                | Descrição                                                                                                                                              | Condições                                                                                                                   |
| ----- | ------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------ | --------------------------------------------------------------------------------------------------------------------------- |
| RN01   | Empréstimo de Livro             | Um bibliotecário pode efetuar um empréstimo para um cliente.                                                                                           | Um livro possui 1 ou mais exemplares, sendo que o exemplar deve ser alugado, e não o livro.                                                                  |
| RN01.1 | Empréstimo com Reserva          | Caso o cliente tenha reservado o livro, o empréstimo é efetuado com sucesso se a posição da reserva do cliente for válida.                             | Número da reserva do cliente < quantidade de reservas disponíveis **e** número de reservas > 0.                             |
| RN01.2 | Empréstimo sem Reserva          | Caso o cliente não tenha reservado o livro, mas deseja alugar imediatamente, o empréstimo pode ser efetuado se determinadas condições forem atendidas. | Reservas disponíveis > 0 **e** livros alugados pelo cliente < limite máximo de empréstimos **e** cliente não está suspenso. |
| RN02   | Reserva de Livro                | Um cliente pode reservar um livro.                                                                                                                     | Livros alugados pelo cliente < limite máximo permitido **e** cliente não está em período de suspensão.                      |
| RN03   | Atualização da Fila de Reservas | Quando um empréstimo previamente reservado é iniciado, a fila de reservas deve ser atualizada.                                                         | O número de reserva de todos os outros clientes deve ser decrementado.                                                      |
| RN04   | Expiração de Reserva            | Uma reserva deve ser encerrada caso o empréstimo não seja iniciado dentro de um período definido.                                                      | Se o empréstimo não for efetuado em **X dias** definidos pelo campo `tempoExpiracaoAgendamento` em **Regras**.              |
| RN05   | Restrição de Role               | Um usuário pode possuir apenas uma role no sistema.                                                                                                    | Role única entre: **CLIENTE**, **BIBLIOTECARIO**, **ADMIN**.                                                                |
| RN06   | Empréstimo de Livro             | Cadastro de usuários clientes no sistema.                                                                                           | Cliente só pode criar uma conta se o CPF inserido for **válido** e o mesmo CPF não estiver cadastrado na conta te um mesmo usuário de mesma role BD. 


##2. DESCRICAO DOS CASOS DE USO

##3. ?
