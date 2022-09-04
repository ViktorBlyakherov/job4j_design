CREATE TABLE IF NOT EXISTS public.cars
(
    id integer NOT NULL DEFAULT nextval('cars_id_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default",
    jeep boolean,
    engine_id integer,
    CONSTRAINT cars_pkey PRIMARY KEY (id),
    CONSTRAINT cars_engine_id_fkey FOREIGN KEY (engine_id)
        REFERENCES public.engines (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

CREATE TABLE IF NOT EXISTS public.engines
(
    id integer NOT NULL DEFAULT nextval('engines_id_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default",
    volume double precision,
    CONSTRAINT engines_pkey PRIMARY KEY (id)
)


select * from cars;

select * from engines;

insert into engines ("name", volume) values ('V6 3.5 litres', 3.5);
insert into engines ("name", volume) values ('1.4 Turbo', 1.4);
insert into engines ("name", volume) values ('V8 6.2 litres', 6.2);

INSERT into cars ("name", jeep, engine_id) VALUES ('Toyota', true, 1);
INSERT into cars ("name", jeep, engine_id) VALUES ('Skoda', false, 2);
INSERT into cars ("name", jeep, engine_id) VALUES ('Mercedes', false, 3);
INSERT into cars ("name", jeep) VALUES ('Nissan', true);

select c.name Название, e.name Описание
from cars c join engines e
on c.engine_id = e.id;

select c.name Название, e.volume Объем
from cars c join engines e
on c.engine_id = e.id;

select c.name Название, c.jeep Джип,
e.name Двигатель,
from cars c join engines e
on c.engine_id = e.id;

