create table usuario(
 id serial NOT NULL UNIQUE,
 nome character varying(150) NOT NULL,
 cpf character varying(14) UNIQUE NOT NULL,
 email character varying(150) NOT NULL,
 login character varying(50) NOT NULL,
 senha character varying(10) NOT NULL,
 data_cadastro TIMESTAMP, 

 CONSTRAINT usuario_pk PRIMARY KEY(id)
);

INSERT INTO usuario(nome, cpf, email, login, senha, data_cadastro) 
VALUES('administrador', '000.000.000-00', 'admin@gmail.com','admin','admin','2022-09-05 11:00:00');

-- criando o campo useradmin
alter table usuario add column useradmin boolean not null default false;

-- criando a chave para controle de usuário
alter table usuario add column usuario_id bigint not null default 1;

alter table usuario add constraint usuario_fk foreign key(usuario_id) references usuario(id);