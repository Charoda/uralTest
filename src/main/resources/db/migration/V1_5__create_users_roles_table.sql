create table if not exists users_roles (
    user_id Bigserial references users(id),
    role_id BIGSERIAL references roles(id)
);

INSERT INTO users_roles (user_id,role_id) values (1,2);
INSERT INTO users_roles (user_id,role_id) values (2,2);
INSERT INTO users_roles (user_id,role_id) values (3,1);

comment on table users_roles is 'Промежуточная таблица для связи users и roles';
comment on column users_roles.user_id is 'внешний ключ на таблицу users ';
comment on column users_roles.role_id is 'внешний ключ на таблицу roles';