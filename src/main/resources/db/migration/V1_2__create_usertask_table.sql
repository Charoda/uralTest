create table if not exists usertask (
    id bigserial PRIMARY KEY,
    name VARCHAR(255),
    status VARCHAR(100),
    creation_date timestamp with time zone,
    modification_date timestamp with time zone,
    information VARCHAR(300),
    project_id bigint references project(id),
    subproject_id bigint references subproject(id),
    user_type VARCHAR(300)
);

comment on table usertask is 'Таблица с задачами для каждого типа';
comment on column usertask.id is 'идентификатор';
comment on column usertask.name is 'название задачи';
comment on column usertask.status is 'Статус задачи';
comment on column usertask.creation_date is 'дата публикации задачи';
comment on column usertask.modification_date is 'дата изменения задачи';
comment on column usertask.information is 'информация для пользователя';
comment on column usertask.project_id is 'внешний ключ на элемент таблицы project';
comment on column usertask.subproject_id is 'внешний ключ на элемент таблицы subproject';
comment on column usertask.user_type is 'целевая принадлежность задачи';