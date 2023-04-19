create table if not exists roles (
   id Bigserial PRIMARY KEY,
   name VARCHAR(100)
);

INSERT INTO ROLES (id, name) values (1,'ADMIN');
INSERT INTO ROLES (id, name) values (2,'USER');

comment on table roles is 'Таблица ролей для обеспечения секьюрности';
comment on column roles.id is 'идентификатор роли';
comment on column roles.name is 'название роли';