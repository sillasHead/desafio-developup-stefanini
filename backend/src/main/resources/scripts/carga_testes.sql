CREATE SCHEMA IF NOT EXISTS H2DB AUTHORIZATION sa;

create table H2DB.equipe
(
    id int not null,
    nome varchar(255) not null
);

create unique index equipe_id_uindex
    on H2DB.equipe (id);

alter table H2DB.equipe
    add constraint equipe_pk
        primary key (id);

alter table H2DB.equipe modify id int auto_increment;

insert into H2DB.equipe values 
(default, 'equipe javista'),
(default, 'equipe pitom');



create table H2DB.pessoa
(
    id int not null,
    nome varchar(255) not null,
    sobrenome varchar(255) not null,
    contato varchar(200) not null,
    email varchar(200) not null,
    cargo int,
    equipe_id int
);

create unique index pessoa_contato_uindex
    on H2DB.pessoa (contato);

create unique index pessoa_email_uindex
    on H2DB.pessoa (email);

create unique index pessoa_id_uindex
    on H2DB.pessoa (id);

alter table H2DB.pessoa
    add constraint pessoa_pk
        primary key (id);

alter table H2DB.pessoa
    add constraint pessoa_fk
        foreign key (equipe_id) 
        references H2DB.equipe (id);

alter table H2DB.pessoa modify id int auto_increment;

insert into H2DB.pessoa values 
(default, 'sillas', 'cavalcante', '983694726', 'sillas.ap16@gmail.com', 2, null),
(default, 'marcos', 'feio', '981981982', 'marcos@gmail.com', 2, 1);