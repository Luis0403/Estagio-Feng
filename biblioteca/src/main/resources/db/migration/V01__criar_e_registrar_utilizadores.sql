	
	CREATE TABLE IF NOT EXISTS utilizador(
		codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
		nome VARCHAR(50) NOT NULL,
		email VARCHAR(50) NOT NULL,
		senha VARCHAR(150) NOT NULL,
		tipo VARCHAR(20) NOT NULL,
		passwordtoken varchar(50)
		
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	INSERT INTO utilizador(nome, email, senha, tipo)
		values('administrador', 'admin@biblioteca.com', '$2a$10$EQ07KrmItE9Fox.DLx.Pz.MGhHqyyQ2H/iEeZB14eeZn5V9gsMrrG', 'ADMIN');
		
	INSERT INTO utilizador(nome, email, senha, tipo)
		values('Cany Estudante', 'cany.studante@biblioteca.com', '$2a$10$EQ07KrmItE9Fox.DLx.Pz.MGhHqyyQ2H/iEeZB14eeZn5V9gsMrrG', 'ESTUDANTE');
	
	INSERT INTO utilizador(nome, email, senha, tipo)
		values('Cany Gestor', 'cany.gestor@biblioteca.com', '$2a$10$EQ07KrmItE9Fox.DLx.Pz.MGhHqyyQ2H/iEeZB14eeZn5V9gsMrrG', 'GESTOR');
	
	INSERT INTO utilizador(nome, email, senha, tipo)
		values('Cany Professor', 'cany.professor@biblioteca.com', '$2a$10$EQ07KrmItE9Fox.DLx.Pz.MGhHqyyQ2H/iEeZB14eeZn5V9gsMrrG', 'PROFESSOR');
		
	
	
