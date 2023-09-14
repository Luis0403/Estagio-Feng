CREATE TABLE livro(
		codigo serial PRIMARY KEY,
		titulo VARCHAR(50) NOT NULL,
		autor VARCHAR(50) NOT NULL,
		disciplina VARCHAR(50),
		editora VARCHAR(50),
		area_de_conhecimento VARCHAR(200),
		descricao VARCHAR(200),
		codigo_utilizador integer NOT NULL,
		FOREIGN KEY (codigo_utilizador) REFERENCES utilizador(codigo)
	);

	CREATE TABLE utilizador(
		codigo serial PRIMARY KEY,
		nome VARCHAR(50) NOT NULL,
		email VARCHAR(50) NOT NULL,
		senha VARCHAR(150) NOT NULL ,
		codigo_tipo integer NOT NULL,
		FOREIGN KEY (codigo_tipo) REFERENCES tipo_utilizador(codigo)
	);
	
	CREATE TABLE tipo_utilizador(
		codigo serial PRIMARY KEY,
		nome VARCHAR(50) NOT NULL
	);
	
	CREATE TABLE gestor (
		codigo serial PRIMARY KEY,
		nome VARCHAR(50) NOT NULL,
		bi VARCHAR(50) NOT NULL,
		data_nascimento date ,
		sexo VARCHAR (50),
		bairro VARCHAR (50),
		numero_celular INTEGER,
		codigo_utilizador integer NOT NULL,
		FOREIGN KEY (codigo_utilizador) REFERENCES tipo_utilizador(codigo)
	);
	
	
	CREATE TABLE professor (
		codigo serial PRIMARY KEY,
		nome VARCHAR(50) NOT NULL,
		bi VARCHAR(50) NOT NULL,
		data_nascimento date ,
		instituicao_de_ensino VARCHAR (50),
		disciplina_lecionada VARCHAR (50),
		bairro VARCHAR (50),
		numero_celular INTEGER,
		codigo_utilizador integer NOT NULL,
		FOREIGN KEY (codigo_utilizador) REFERENCES tipo_utilizador(codigo)
	);
	CREATE TABLE estudante (
		codigo serial PRIMARY KEY,
		nome VARCHAR(50) NOT NULL,
		bi VARCHAR(50) NOT NULL,
		data_nascimento date ,
		instituicao_de_ensino VARCHAR (50),
		curso VARCHAR (50),
		bairro VARCHAR (50),
		numero_celular INTEGER,
		codigo_utilizador integer NOT NULL,
		FOREIGN KEY (codigo_utilizador) REFERENCES tipo_utilizador(codigo)
	);
	