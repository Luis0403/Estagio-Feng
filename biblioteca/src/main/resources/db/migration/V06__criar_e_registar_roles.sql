create table role(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT, 
	nome VARCHAR(50) NOT NULL
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 insert into role(nome) values ('ROLE_ADMIN'),('ROLE_GESTOR'),('ROLE_PROFESSOR'),('ROLE_ESTUDANTE');