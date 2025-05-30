-- Создание SEQUENCE для users
CREATE SEQUENCE users_id_seq START 1;
CREATE SEQUENCE roles_id_seq START 1;

-- Создание таблицы комнат
CREATE TABLE rooms
(
    room_number INTEGER PRIMARY KEY,
    capacity    INTEGER NOT NULL -- Вместимость комнаты
);

CREATE TABLE roles
(
    id   BIGINT PRIMARY KEY DEFAULT nextval('roles_id_seq'),
    name VARCHAR(50) NOT NULL -- Название роли
);


-- Создание таблицы пользователей
CREATE TABLE users
(
    id            BIGINT PRIMARY KEY DEFAULT nextval('users_id_seq'),
    first_name    VARCHAR(50)         NOT NULL,
    last_name     VARCHAR(50)         NOT NULL,
    age           INTEGER             NOT NULL CHECK (age >= 16),
    group_number  VARCHAR(20),                            -- Группа студента
    room_number   INTEGER REFERENCES rooms (room_number), -- Комната в общежитии
    email         VARCHAR(100) UNIQUE NOT NULL,           -- Для авторизации
    password_hash VARCHAR(255)        NOT NULL            -- Хранение хэша пароля
);

CREATE TABLE users_roles
(
    user_id BIGINT REFERENCES users (id) ON DELETE CASCADE,
    role_id BIGINT REFERENCES roles (id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id)
);

-- Создание SEQUENCE для events
CREATE SEQUENCE events_id_seq START 1;

-- Создание таблицы мероприятий
CREATE TABLE events
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('events_id_seq'),
    title       VARCHAR(100) NOT NULL,        -- Название мероприятия
    description TEXT,                         -- Описание мероприятия
    event_date  VARCHAR      NOT NULL,        -- Дата и время мероприятия
    location    VARCHAR(100) NOT NULL,        -- Место проведения
    created_by  INTEGER REFERENCES users (id) -- Кто создал мероприятие
);

-- Создание SEQUENCE для event_registrations
CREATE SEQUENCE event_registrations_id_seq START 1;

-- Создание таблицы регистрации на мероприятия
CREATE TABLE event_registrations
(
    id            INTEGER PRIMARY KEY DEFAULT nextval('event_registrations_id_seq'),
    event_id      INTEGER NOT NULL REFERENCES events (id) ON DELETE CASCADE, -- Мероприятие
    user_id       INTEGER NOT NULL REFERENCES users (id) ON DELETE CASCADE,  -- Участник
    registered_at TIMESTAMP           DEFAULT CURRENT_TIMESTAMP              -- Дата регистрации
);

-- Создание SEQUENCE для complaints
CREATE SEQUENCE complaints_id_seq START 1;

-- Создание таблицы жалоб/обращений
CREATE TABLE complaints
(
    id             INTEGER PRIMARY KEY DEFAULT nextval('complaints_id_seq'),
    user_id        INTEGER NOT NULL REFERENCES users (id) ON DELETE CASCADE, -- Кто отправил жалобу
    complaint_text TEXT    NOT NULL,                                         -- Текст жалобы
    status         VARCHAR(20)         DEFAULT 'В процессе'                  -- Статус (pending, resolved)
);

INSERT INTO rooms (room_number, capacity)
VALUES (0, 1);

INSERT INTO roles(name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

INSERT INTO users (first_name, last_name, age, group_number, room_number, email, password_hash)
VALUES ('Admin', 'Admin', 20, '11-111', 0, 'admin@admin.com',
        '$2a$10$uwpiNiO.PlYoLjt9gfv7ROxDfYiENYC0aQjXkGQd4YAWN8MIIetA.');
