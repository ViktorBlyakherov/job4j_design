create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

INSERT into devices ("name", price) values ('PC', 800);
INSERT into devices ("name", price) values ('Laptop', 1200);
INSERT into devices ("name", price) values ('Scanner', 300);
INSERT into devices ("name", price) values ('Printer', 500);

select * from devices;

insert into people ("name") values ('Ivanov');
insert into people ("name") values ('Petrov');
insert into people ("name") values ('Sidorov');

select * from people;

insert into devices_people (device_id, people_id) values (1, 1);
insert into devices_people (device_id, people_id) values (1, 3);
insert into devices_people (device_id, people_id) values (2, 2);
insert into devices_people (device_id, people_id) values (3, 1);
insert into devices_people (device_id, people_id) values (4, 3);

select avg(price) from devices;

select p.name, avg(d.price) from people p
	join devices_people dp ON dp.people_id = p.id
	JOIN devices d ON d.id = dp.device_id group by p."name";


select p.name, avg(d.price) from people p
	join devices_people dp ON dp.people_id = p.id
	JOIN devices d ON d.id = dp.device_id group by p."name" having avg(d.price) > 1000;



