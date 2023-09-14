	
	CREATE TABLE IF NOT EXISTS livro(
		codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
		titulo VARCHAR(200) NOT NULL,
		autor VARCHAR(200) NOT NULL,
		disciplina VARCHAR(200) NOT NULL,
		editora VARCHAR(200),
		area_de_conhecimento VARCHAR(200) NOT NULL,
		descricao VARCHAR(200),
		codigo_utilizador BIGINT(20) NOT NULL,
		FOREIGN KEY (codigo_utilizador) REFERENCES  utilizador(codigo)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	INSERT INTO livro(titulo, autor, disciplina, editora,area_de_conhecimento, codigo_utilizador)
		values('Dark Web.pdf', 'Babar Akghar, Marco Gercke, Stefanos et al.', 'Security Informatics Law Enforcement', 
		'Springer','Seguranca de Informacao', 1);
		
			INSERT INTO livro(titulo, autor, disciplina, editora,area_de_conhecimento, codigo_utilizador)
		values('DeepLearningPractitionersApproach.pdf', 'Josh Patterson && Adam Gibson', 'Machine Learning', 
		"O'REILLY",'Inteligencia Artificial', 1);
		
			INSERT INTO livro(titulo, autor, disciplina, editora,area_de_conhecimento, codigo_utilizador)
		values('Python_for_Secret_Agents.pdf', 'Steven F. Lott', 'Python', 'PACKT','Analyze, encrypt, and unrecover intelligence data using python', 1);
		
		