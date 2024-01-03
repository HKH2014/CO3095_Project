create database hkh6;
use hkh6;

CREATE TABLE seller_details (
username varchar(100) primary key,
Pass varchar(120),
LastName varchar(255),
FirstName varchar(255),
user_role varchar(20),
user_rating float (5)
);

-- password: apple 
insert into seller_details (username, Pass, LastName, Firstname, user_role, user_rating)
values ('LLOY16' , '3a7bd3e2360a3d29eea436fcfb7e44c735d117c42d1c1835420b6b9942dd4f1b' , 'Mikhel', 'Samantha', 'buyer', 4.7);

-- password: Sword1
insert into seller_details (username, Pass, LastName, Firstname, user_role, user_rating)
values ('R2D2BB8' , 'd6fcc1d88fa3318891ff6a5dff0d8313e1372557df853ac4bb544ce193c8a3ce' , 'Skywalker', 'Anakin', 'seller', 4.7);

-- password: Queen
insert into seller_details (username, Pass, LastName, Firstname, user_role, user_rating)
values ('qwerty' , '153f6e435589b1c8e8d9d55a901ea79d7d9810208107516b1993cc1b01a57b84' , 'Smitty', 'James', 'admin', 4.7);

-- password: brother
insert into seller_details (username, Pass, LastName, Firstname, user_role, user_rating)
values ('PC212', '0b4bd77cec705cf5df016344c0fc673fb3c2240078e6af572b2c5c6fa76114af', 'Rayyaz', 'Zara', 'buyer', 2.1);

-- password: 123456
insert into seller_details (username, Pass, LastName, Firstname, user_role, user_rating)
values ('abc', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'Smith', 'Bob', 'seller', 2.1);

-- password: Phazon
insert into seller_details (username, Pass, LastName, Firstname, user_role, user_rating)
values ('SR388', '446f3afaec43a53bde1494db52ac517d2bfda31a1d0f40dd89f4db95eb814dd3', 'Aran', 'Jonas', 'admin', 2.1);

DELETE FROM seller_details WHERE username='R2D2BB8';

select * from seller_details;

SELECT * FROM property_history ORDER BY final_sale_price ASC;

UPDATE seller_details SET user_rating=4 WHERE username = "PC212";


CREATE TABLE properties (
property_ID int(10) primary key,
property_name varchar (200),
property_description varchar (5000),
num_beds int(2),
num_bathrooms int(2),
start_bid int (10),
end_date date,
highest_bid int(10),
num_bidders int(5) );

insert into properties (property_ID, property_name, property_description, num_beds, num_bathrooms, start_bid, end_date, highest_bid, num_bidders)
values (001, 'Two bed terrice house' , 'A lovely house by the riverside, ideal for a small family with ensuite bedrooms!' , 2, 2, 300000, '2024-08-20', 600000, 17 );

insert into properties (property_ID, property_name, property_description, num_beds, num_bathrooms, start_bid, end_date, highest_bid, num_bidders)
values (002, 'Five-bed semi detached house' , 'In need of renovations but has amazing potential and very good bones. Right by the river and a 10 min walk from the train station' , 5, 2, 500000, '2024-06-16', 700000, 12 );

insert into properties (property_ID, property_name, property_description, num_beds, num_bathrooms, start_bid, end_date, highest_bid, num_bidders)
values (003, 'Five bed semi-detached house' , 'Right by the trainstation and the town centre' , 5, 2, 650000, '2023-08-20', 600000, 9 );

insert into properties (property_ID, property_name, property_description, num_beds, num_bathrooms, start_bid, end_date, highest_bid, num_bidders)
values (004, 'Studio flat' , 'Recenly refurbished and ready for the market' , 1, 1, 650000, '2023-08-20', 230000, 28 );

insert into properties (property_ID, property_name, property_description, num_beds, num_bathrooms, start_bid, end_date, highest_bid, num_bidders)
values (005, '3 bed apartment' , 'With washbasins in every room by the parkside' , 3, 1, 450000, '2023-08-20', 230000, 18 );

insert into properties (property_ID, property_name, property_description, num_beds, num_bathrooms, start_bid, end_date, highest_bid, num_bidders)
values (006, '4 ensuite luxury apartment' , 'Modern and new with double glazed windows' , 4, 4, 650000, '2023-08-20', 10000000, 15 );

insert into properties (property_ID, property_name, property_description, num_beds, num_bathrooms, start_bid, end_date, highest_bid, num_bidders)
values (007, 'Annex behind a semi detached house' , 'Sustainable for a young couple' , 1, 1, 400000, '2023-08-20', 10000000, 15 );

select * from properties;






CREATE TABLE property_history (
property_ID int(10) primary key,
item_name varchar (100),
final_sale_price int(10),
winning_bidder varchar (10),
sale_date date
);

insert into property_history (property_ID, item_name, final_sale_price, winning_bidder, sale_date) 
values(0011, '3 bed apartment', 400500, 'PC212' , '2023-12-12');

insert into property_history (property_ID, item_name, final_sale_price, winning_bidder, sale_date) 
values (0012, '2 bed house Dawlish Road', 3800500, 'LLOY16' , '2022-07-09');

insert into property_history (property_ID, item_name, final_sale_price, winning_bidder, sale_date) 
values (0013, 'Five bed semi-detached house', 48000500, 'NO14N' , '2022-06-19');

insert into property_history (property_ID, item_name, final_sale_price, winning_bidder, sale_date) 
values (0014, 'A mansion', 498000500, 'TICK37' , '2022-06-19');

insert into property_history (property_ID, item_name, final_sale_price, winning_bidder, sale_date) 
values (0015, '4 bed bunglow', 48000500, 'L085TAR' , '2022-06-19');

insert into property_history (property_ID, item_name, final_sale_price, winning_bidder, sale_date) 
values (0016, '3 bed family bedroom', 48000500, 'PC212' , '2022-06-19');

select * from property_history;


