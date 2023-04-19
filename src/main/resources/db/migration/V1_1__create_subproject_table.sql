create table if not exists subproject (
   id Bigserial PRIMARY KEY,
   name VARCHAR(100),
    project_id BIGINT not null,
    FOREIGN KEY (project_id) REFERENCES project(id)
);

comment on table subproject is 'Таблица подпроектов';
comment on column subproject.id is 'идентификатор подпроекта';
comment on column subproject.name is 'название подпроекта';
comment on column subproject.project_id is 'внешний ключ на элемент в таблице проектов';