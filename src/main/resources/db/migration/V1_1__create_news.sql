-- Создание SEQUENCE для roles
CREATE SEQUENCE news_id_seq START 1;

-- Создание таблицы ролей
CREATE TABLE news (
            id INTEGER PRIMARY KEY DEFAULT nextval('news_id_seq'),
            title VARCHAR NOT NULL,
            description VARCHAR NOT NULL,
            image_url VARCHAR NOT NULL

);
