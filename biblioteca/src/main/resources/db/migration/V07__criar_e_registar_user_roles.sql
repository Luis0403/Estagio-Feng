create  table IF NOT EXISTS utilizador_roles(
		utilizador_codigo BIGINT(20) NOT NULL,
		roles_codigo BIGINT(20) NOT NULL,
		FOREIGN KEY (utilizador_codigo) REFERENCES  utilizador(codigo),
		FOREIGN KEY (roles_codigo) REFERENCES  role(codigo)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;
		
		
insert into utilizador_roles (utilizador_codigo,roles_codigo) values (1,1),(2,4),(3,2),(4,3);
