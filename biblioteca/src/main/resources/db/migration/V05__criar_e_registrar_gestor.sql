create table gestor(
		codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT, 
		nome VARCHAR(50) NOT NULL,
		sexo VARCHAR(50) NOT NULL,
		foto VARCHAR(200),
		numero_de_bi VARCHAR(13) NOT NULL ,
		data_de_nascimento DATE NOT NULL ,
		celular VARCHAR(50),
		codigo_utilizador BIGINT(20) NOT NULL,
		FOREIGN KEY (codigo_utilizador) REFERENCES  utilizador(codigo)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO gestor(nome,sexo,  numero_de_bi, data_de_nascimento,    celular, codigo_utilizador)
		values("Lucas Manhica", "MASCULINO",   "65656561L", "1945-04-06",  "8265656", 1);
		

INSERT INTO gestor(nome, sexo,  numero_de_bi, data_de_nascimento,   celular, codigo_utilizador)
		values("Luis Mucavel", "FEMININO",  "66556561L", "1986-09-06", "89865623", 2);


INSERT INTO gestor(nome, sexo,  numero_de_bi, data_de_nascimento,    celular, codigo_utilizador)
		values("Mariamo Damiao", "MASCULINO", "6665561L", "1976-07-08", "87656232", 3);
		

INSERT INTO gestor(nome, sexo,  numero_de_bi, data_de_nascimento,    celular, codigo_utilizador)
		values("Cany Mangeu", "FEMININO" , "66655681L", "1936-07-07",  "84565236", 4);
		
		
