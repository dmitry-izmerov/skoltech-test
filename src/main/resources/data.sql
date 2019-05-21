insert into subjects(id, name) values
    (1, 'subject 1'),
    (2, 'subject 2'),
    (3, 'subject 3'),
    (4, 'subject 4');

alter sequence subjects_id_seq restart with 5;

insert into sensors(id, name) values
    (1, 'sensor 1'),
    (2, 'sensor 2'),
    (3, 'sensor 3'),
    (4, 'sensor 4');

alter sequence sensors_id_seq restart with 5;

insert into measurements(id, time, value, sensor_id, subject_id) values
    (1, '2019-02-19 20:00:00', 55.25, 1, 1),
    (2, '2019-02-19 20:00:00', 55.42, 2, 2),
    (3, '2019-02-19 20:00:00', 55.2, 3, 3),
    (4, '2019-02-19 16:00:00', 20.2, 4, 4),
    (5, '2019-02-19 20:00:00', 30.5, 4, 4),
    (6, '2019-03-19 20:00:00', 40.2, 4, 4);

alter sequence measurements_id_seq restart with 7;
