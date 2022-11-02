use alurarest;

insert into usuario (id, email, nome, senha) values (100, 'teste@teste.com.br', 'Fulano de Tal', '123456');

insert into curso (id, nome, categoria) values 
(201, 'Spring Boot', 'Programação'),
(202, 'HTML5','Front-End');

insert into topico (id, titulo, mensagem, data_criacao, status, autor_id, curso_id) values
(301, 'Dúvida 1', 'Erro ao criar projeto', '2022-01-03', 'NAO_RESPONDIDO', 100, 201),
(302, 'Dúvida 1', 'Projeto não compila', '2022-02-01', 'NAO_SOLUCIONADO', 100, 201),
(303, 'Dúvida 1', 'Tag HTML', '2022-01-05', 'SOLUCIONADO', 100, 202);
