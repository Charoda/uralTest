create table if not exists users (
       id Bigserial PRIMARY KEY,
       username VARCHAR(100),
       password VARCHAR(300),
       email VARCHAR(300)
);

--У всех пароль = 100
INSERT INTO users (id, username, password,email) values (1,'Petr','$2a$12$qp439KcD0Lovv00Lt2uv6ek6ZBNGTHEF0IsjTy2JJt61mIf64pp7K','petr@mail.ru');
INSERT INTO users (id, username, password,email) values (2,'Akhmed','$2a$12$qp439KcD0Lovv00Lt2uv6ek6ZBNGTHEF0IsjTy2JJt61mIf64pp7K','akhmed@mail.ru');
INSERT INTO users (id, username, password,email) values (3,'Aleskandr','$2a$12$qp439KcD0Lovv00Lt2uv6ek6ZBNGTHEF0IsjTy2JJt61mIf64pp7K','aleks@mail.ru');

comment on table users is 'Таблица пользователей';
comment on column users.id is 'идентификатор пользователя';
comment on column users.username is 'имя пользователя';
comment on column users.password is 'пароль пользователя';
comment on column users.email is 'email пользователя';