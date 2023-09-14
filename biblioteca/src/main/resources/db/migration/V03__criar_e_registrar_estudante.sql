create table IF NOT EXISTS estudante(
   	    codigo BigInt primary key AUTO_INCREMENT,
  		nome VARCHAR(50) NOT NULL,
		sexo VARCHAR(50) NOT NULL,
		foto VARCHAR(200),
		numero_de_bi VARCHAR(13) NOT NULL ,
		data_de_nascimento DATE NOT NULL ,
		celular VARCHAR(50),
		instituicao_de_ensino varchar(100) not null ,
		curso varchar(250) not null,
	    codigo_utilizador BigInt not null,
	    foreign key (codigo_utilizador)references utilizador(codigo)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

