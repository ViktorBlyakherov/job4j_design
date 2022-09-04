create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
    type_id INT REFERENCES type(id),
	expired_date date,
	price float
);

insert into type (name) values ('Молоко'), ('Сыр'), ('Мясо');

select * from type;

insert into product (name, type_id, expired_date, price) values
		('Сметана', 1, '10-09-2022', 120),
		('Йогурт', 1, '03-09-2022', 80),
		('Мороженое', 1, '08-09-2022', 50),
		('Сыр плавленный', 2, '10-09-2022', 30),
		('Моцарелла', 2, '15-09-2022', 250),
		('Говядина', 3, '06-09-2022', 500),
		('Свинина', 3, '01-09-2022', 400);

select * from product;

select * from product p
join type t
ON t.id = p.type_id
where t.name = 'Сыр';

select * from product p
where p.name like '%Мороженое%';

select * from product p
where p.expired_date < now();

select p.* from product p
where p.price = (Select max(price) from product);

select t.name, count (p.id)
from type t join product p
on p.type_id = t.id
group by t.name;

select p.* from product p
join type t
on p.type_id = t.id
where t.name = 'Сыр' or t.name = 'Молоко';

select t.name from type t
JOIN product p
on t.id = p.type_id
group by t.name
having count(p.id) < 10;

select p.name, p.price,
p.expired_date, t.name Тип
from product p join type t
on p.type_id = t.id;

