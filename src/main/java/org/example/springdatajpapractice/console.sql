create table artists
   (
       id         serial primary key,
       first_name varchar(50) not null,
       last_name  varchar(50) not null,
       email      varchar(50) not null unique,
       country    varchar(50) not null -- (KAZAKHSTAN, USA, ...)
   );

create table songs
(
    id           serial primary key,
    title        varchar(100) not null,
    duration     integer      not null, -- секунды
    genre        varchar(40)  not null, -- (ROCK, POP, INDIE)
    created_at   date         not null,
    release_year integer      not null,
    playcount    integer default 0,
    artist_id    integer references artists (id) not null
);

INSERT INTO artists (first_name, last_name, email, country) VALUES
                                                                ('John',     'Doe',        'john.doe@mail.com',        'USA'),
                                                                ('Emily',    'Stone',      'emily.stone@mail.com',     'UK'),
                                                                ('Alex',     'Turner',     'alex.turner@mail.com',     'UK'),
                                                                ('Lana',     'Delrey',     'lana.delrey@mail.com',     'USA'),
                                                                ('Daniel',   'Kim',        'daniel.kim@mail.com',      'SOUTH_KOREA'),
                                                                ('Aigerim',  'Sadykova',   'aigerim@mail.kz',          'KAZAKHSTAN'),
                                                                ('Nursultan','Bekov',      'nursultan@mail.kz',        'KAZAKHSTAN'),
                                                                ('Ivan',     'Petrov',     'ivan.petrov@mail.ru',     'RUSSIA'),
                                                                ('Maria',    'Gonzalez',   'maria.g@mail.com',         'SPAIN'),
                                                                ('Lucas',    'Moreau',     'lucas.moreau@mail.com',    'FRANCE');

INSERT INTO songs
(title, duration, genre, created_at, release_year, playcount, artist_id) VALUES
                                                                             ('Midnight Drive',   210, 'ROCK',  '2018-06-12', 2018, 125000, 1),
                                                                             ('Lost Signals',     185, 'INDIE', '2019-03-08', 2019,  98000, 1),
                                                                             ('Neon Lights',      200, 'POP',   '2020-07-19', 2020, 230000, 2),
                                                                             ('Paper Hearts',     190, 'POP',   '2022-02-02', 2022, 410000, 2),
                                                                             ('Echoes',           240, 'ROCK',  '2017-09-22', 2017,  76000, 3),
                                                                             ('Static Dreams',    245, 'INDIE', '2017-01-21', 2017,  67000, 3),
                                                                             ('Golden Hour',      195, 'POP',   '2021-05-11', 2021, 310000, 4),
                                                                             ('Electric Skin',    195, 'POP',   '2023-03-10', 2023, 275000, 4),
                                                                             ('Cold City',        225, 'INDIE', '2016-11-03', 2016,  54000, 5),
                                                                             ('Moonlit Streets',  210, 'INDIE', '2022-06-14', 2022,  56000, 5),
                                                                             ('Desert Wind',      260, 'ROCK',  '2015-08-14', 2015,  42000, 6),
                                                                             ('Summer Fade',      205, 'POP',   '2018-04-18', 2018,  99000, 6),
                                                                             ('Broken Mirrors',   235, 'ROCK',  '2019-12-09', 2019,  88000, 7),
                                                                             ('Wild Fire',        230, 'ROCK',  '2021-09-01', 2021, 134000, 7),
                                                                             ('Satellite Love',   215, 'INDIE', '2020-10-27', 2020, 158000, 8),
                                                                             ('Echo Chamber',     220, 'INDIE', '2021-11-19', 2021,  91000, 8),
                                                                             ('Night Runner',     205, 'POP',   '2022-07-08', 2022, 201000, 9),
                                                                             ('Golden Skies',     215, 'POP',   '2023-05-16', 2023, 188000, 9),
                                                                             ('Northern Lights',  240, 'ROCK',  '2020-01-13', 2020, 112000, 10),
                                                                             ('Last Horizon',     250, 'ROCK',  '2024-04-02', 2024,  67000, 10);

create table tasks
(
    id          bigserial
        primary key,
    title       varchar(100)  not null,
    description varchar(5000) not null,
    priority    varchar(20)   not null,
    status      varchar(20)   not null,
    assigned_to integer       not null references users (id),
    created_by  integer       not null references users (id),
    start_date  date          not null,
    end_date    date          not null,
    created_at  timestamp     not null
);


--task2
create table hosts
   (
       id           serial primary key,
       first_name   varchar(50) not null,
       last_name    varchar(50) not null,
       email        varchar(100) not null unique,
       country      varchar(50) not null,
       subscribers  integer not null default 0
   );

create table podcasts
(
    id            serial primary key,
    title         varchar(150) not null,
    description   text,
    category      varchar(40) not null, -- TECH, BUSINESS, EDUCATION
    language      varchar(10) not null, -- EN, RU, KZ
    duration      integer not null,      -- минуты
    release_year  integer not null,
    host_id       integer references hosts(id) not null
);
-- hosts
insert into hosts (first_name, last_name, email, country, subscribers)
values
    ('Alex', 'Johnson', 'alex.johnson@mail.com', 'USA', 12000),
    ('Ivan', 'Petrov', 'ivan.petrov@mail.ru', 'Russia', 8500),
    ('Aruzhan', 'Sadykova', 'aruzhan.s@mail.kz', 'Kazakhstan', 4300);

-- podcasts
insert into podcasts (title, description, category, language, duration, release_year, host_id)
values
    ('Tech Today', 'Latest news and trends in technology', 'TECH', 'EN', 45, 2023, 1),
    ('Business Inside', 'How modern businesses grow', 'BUSINESS', 'EN', 60, 2022, 1),
    ('Образование просто', 'Разговоры об обучении и саморазвитии', 'EDUCATION', 'RU', 50, 2021, 2),
    ('Qazaq Education', 'Подкаст про образование в Казахстане', 'EDUCATION', 'KZ', 40, 2024, 3);

insert into podcasts (title, description, category, language, duration, release_year, host_id)
values
    ('Cloud Basics', 'Основы облачных технологий', 'TECH', 'EN', 40, 2021, 1),
    ('Java Deep Dive', 'Глубокое погружение в Java', 'TECH', 'EN', 70, 2023, 1),
    ('Microservices Talk', 'Архитектура микросервисов', 'TECH', 'EN', 55, 2024, 1),

    ('Marketing 101', 'Базовый маркетинг для бизнеса', 'BUSINESS', 'EN', 45, 2020, 2),
    ('Продажи без воды', 'Практика эффективных продаж', 'BUSINESS', 'RU', 50, 2022, 2),

    ('Учись учиться', 'Методы эффективного обучения', 'EDUCATION', 'RU', 35, 2019, 3),
    ('Data Science Start', 'Введение в Data Science', 'EDUCATION', 'EN', 60, 2023, 3),

    ('Digital Qazaq', 'Цифровые технологии на казахском', 'TECH', 'KZ', 42, 2024, 3),
    ('Soft Skills', 'Коммуникация и личная эффективность', 'BUSINESS', 'RU', 38, 2021, 1),

    ('Backend Roadmap', 'Путь backend-разработчика', 'TECH', 'EN', 65, 2022, 2),
    ('Finance for IT', 'Финансы для айтишников', 'BUSINESS', 'EN', 48, 2023, 1),

    ('История науки', 'Как развивалась наука', 'EDUCATION', 'RU', 55, 2020, 2),
    ('Learning Fast', 'Как учиться быстрее', 'EDUCATION', 'EN', 33, 2024, 3);

