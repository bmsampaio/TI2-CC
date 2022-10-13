CREATE TABLE produto (
    id serial PRIMARY KEY, 
    nome VARCHAR(50),
    quantidade INTEGER 
);
INSERT INTO produto (id,nome,quantidade) VALUES (1,'batom',5);
INSERT INTO produto (id,nome,quantidade) VALUES (2,'cremes',10);
INSERT INTO produto (id,nome,quantidade) VALUES (3,'sombra',8);

select * from produto