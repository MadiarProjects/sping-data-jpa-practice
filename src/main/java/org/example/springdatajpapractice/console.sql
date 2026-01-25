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
