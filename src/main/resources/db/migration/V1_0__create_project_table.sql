create table if not exists project (
     id Bigserial PRIMARY KEY,
     name VARCHAR(100)
);

comment on table project is 'Таблица проектов';
comment on column project.id is 'идентификатор проекта';
comment on column project.name is 'название проекта';