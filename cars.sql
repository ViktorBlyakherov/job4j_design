create table japan_cars(

      id serial primary key,

      name VARCHAR(255),

      jeep BOOLEAN,

	  description text

);

INSERT into japan_cars ("name", jeep, description) VALUES('Toyota', FALSE, 'Toyota Camry 3.5');

SELECT * from japan_cars

update japan_cars set jeep = True, description = 'Toyota Land Cruiser 200';

DELETE from japan_cars