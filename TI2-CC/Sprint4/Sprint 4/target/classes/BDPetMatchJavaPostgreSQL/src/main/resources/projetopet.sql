


CREATE SCHEMA PetAlert;

-- -----------------------------------------------------
-- Table user
-- -----------------------------------------------------
CREATE TABLE USUARIO (
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
  nome VARCHAR(45)
  email VARCHAR(45),
  site VARCHAR(45),
);

-- -----------------------------------------------------
-- Table animal
-- -----------------------------------------------------
CREATE TABLE ANIMAL (
  id_Animal serial PRIMARY KEY,
  dono VARCHAR(45),
  especie VARCHAR(45),
  porte VARCHAR(45),
  idade INT,
  nome VARCHAR(45),
  caracteristicas VARCHAR(45),
  raca VARCHAR(45),
  usuario_e_mail VARCHAR(40),
  ong_cnpj VARCHAR(14)
);

/* ADD BASE DATA */

INSERT INTO USUARIO (email,nome,sobrenome,endereço,telefone,senha) VALUES ('b.m.sampaio','Barbara','Sampaio','Belo Horizonte','31992993340','12345678');
INSERT INTO ONG (cnpj, nome, email, site) VALUE ('16.360.548/0001-82', 'ONG1', 'e.mail@email.com', 'ONG1.com')
INSERT INTO ANIMAL (id_Animal,dono,especie,porte,idade,nome,caracteristicas,raca,usuario_e_mail, ong_cnpj) VALUES (1,'Barbara Sampaio','Cachorro','Pequeno',3,'Pingo','Carinhoso e alegre','SRD','b.m.sampaio', '19.852.832/0001-64');

/* ADD CONSTRAINTS */

ALTER TABLE USUARIO
  ADD CONSTRAINT PK_USUARIO PRIMARY KEY (email);

ALTER TABLE ONG
  ADD CONSTRAINT PK_ONG PRIMARY KEY (cnpj);

ALTER TABLE ANIMAL
  ADD CONSTRAINT PK_USUARIO PRIMARY KEY (id_Animal);