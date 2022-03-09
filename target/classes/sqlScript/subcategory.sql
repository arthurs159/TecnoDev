INSERT INTO Subcategory
(name, code, order_in_system, description, active, category_id)
VALUES ('Java', 'java', 1, 'Java é uma grande plataforma presente em todo lugar: de corporações à bancos e governo. Desenvolva aplicações robustas com um back-end e construa APIs.', true, (SELECT id from Category WHERE code = 'programacao'));

INSERT INTO Subcategory
(name, code, order_in_system, description, active, category_id)
VALUES ('Java e Persistência', 'java-e-persistencia', 2, '', true, (SELECT id from Category WHERE code = 'programacao'));

INSERT INTO Subcategory
(name, code, order_in_system, description, active, category_id)
VALUES ('PHP', 'php', 3, 'PHP é uma das linguagens mais utilizadas.', true, (SELECT id from Category WHERE code = 'programacao'));

INSERT INTO Subcategory
(name, code, order_in_system, description, active, category_id)
VALUES ('COBOL', 'cobol', 0, '', false, (SELECT id from Category WHERE code = 'programacao'));

INSERT INTO Subcategory
(name, code, order_in_system, description, active, category_id)
VALUES ('Builds e Controle de versão', 'builds-e-controle-de-versao', 1, 'As ferramentas mais utilizadas para desenvolvimento: controle de versão com Git e Github além de build da aplicação através de Maven.', true,(SELECT id from Category WHERE code = 'devops'));