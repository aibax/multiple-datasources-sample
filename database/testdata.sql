CREATE TABLE IF NOT EXISTS customer
(
	customer_id int primary key auto_increment,
	last_name varchar(30),
	first_name varchar(30)
);

CREATE TABLE IF NOT EXISTS staff
(
	staff_id int primary key auto_increment,
	name varchar(50)
);

INSERT INTO customer (last_name, first_name) VALUES ('源', '静香');
INSERT INTO customer (last_name, first_name) VALUES ('剛田', '武');
INSERT INTO customer (last_name, first_name) VALUES ('骨川', 'スネ夫');
INSERT INTO customer (last_name, first_name) VALUES ('出木杉', '英才');

INSERT INTO staff (name) VALUES ('野比 のび太');
INSERT INTO staff (name) VALUES ('ドラえもん');
INSERT INTO staff (name) VALUES ('野比 玉子');
INSERT INTO staff (name) VALUES ('野比 のび助');
INSERT INTO staff (name) VALUES ('ドラミ');
