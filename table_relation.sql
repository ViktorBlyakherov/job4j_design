create TABLE engines(
    id serial PRIMARY key,
	name VARCHAR(255),
	volume FLOAT
);

create table cars(

      id serial primary key,

      name VARCHAR(255),

      jeep BOOLEAN,

	  engine_id int REFERENCES engines(id)

);

create table owners(

      id serial primary key,

      name VARCHAR(255)
);

create table owners_cars(

      id serial primary key,

      owners_id int REFERENCES owners(id),
	cars_id int REFERENCES cars(id)
);

create table reg_numbers(

      id serial primary key,

      reg_number VARCHAR(20)
);

create table cars_reg_numbers(

      id serial primary key,

      reg_numbers_id int REFERENCES reg_numbers(id) UNIQUE,
	cars_id int REFERENCES cars(id) UNIQUE
);



