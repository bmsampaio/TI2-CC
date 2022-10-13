
-- -----------------------------------------------------
-- Table user
-- -----------------------------------------------------
CREATE TABLE usuarios (
  email VARCHAR(45) PRIMARY KEY,
  nome VARCHAR(45),
  sobrenome VARCHAR(45),
  endereço VARCHAR(45),
  telefone VARCHAR(12),
  senha VARCHAR(45)
);




-- -----------------------------------------------------
-- Table ONG
-- -----------------------------------------------------
CREATE TABLE ONG (
  cnpj VARCHAR(14) PRIMARY KEY,
  email VARCHAR(45),
  site VARCHAR(45),
  nome VARCHAR(45)
);

-- -----------------------------------------------------
-- Table animal
-- -----------------------------------------------------
CREATE TABLE animal (
  id_Animal serial PRIMARY KEY,
  dono VARCHAR(45),
  especie VARCHAR(45),
  porte VARCHAR(45),
  idade INT,
  nome VARCHAR(45),
  caracteristicas VARCHAR(45),
  raca VARCHAR(45),
  usuario_e_mail VARCHAR(40),
  ONG_cnpj VARCHAR(14)
);

INSERT INTO usuarios (email,nome,sobrenome,endereço,telefone,senha) VALUES ('b.m.sampaio','Barbara','Sampaio','Belo Horizonte','31992993340','12345678');
INSERT INTO animal (id_animal,dono,especie,porte,idade,nome,caracteristicas,raca,usuario_e_mail) VALUES (1,'Barbara Sampaio','Cachorro','Pequeno',3,'Pingo','Carinhoso e alegre','SRD','b.m.sampaio');

select* from usuarios;
select* from animal;