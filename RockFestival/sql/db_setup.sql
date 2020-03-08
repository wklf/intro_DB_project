-- creates database and adds some dummy data
-- create database intro_db_proj;

-- add drop if exists to have a clean version each time?
drop table if exists Staff cascade;
drop table if exists Band cascade;
drop table if exists Stage cascade;
drop table if exists Concert cascade;
drop table if exists Member cascade;
drop table if exists Plays_in cascade;
drop table if exists In_charge cascade;

CREATE TABLE Staff (pno text PRIMARY KEY, name text, address text);
CREATE TABLE Band (name text PRIMARY KEY, country text, info text,
    contact_person text REFERENCES Staff(pno));
CREATE TABLE Stage (name text PRIMARY KEY, capacity int);
CREATE TABLE Concert (start_time timestamp, stop_time timestamp,
    stage_name text REFERENCES Stage(name), band_name text REFERENCES Band(name),
	PRIMARY KEY (start_time, band_name));
CREATE TABLE In_charge (staff_pno text, stage_name text REFERENCES Stage(name),
    start_time timestamp, stop_time timestamp, PRIMARY KEY (stage_name, start_time));
CREATE TABLE Member (email text PRIMARY KEY, name text, biography text);
CREATE TABLE Plays_in (band_name text REFERENCES Band(name),
    member_email text REFERENCES Member(email));

INSERT INTO Band (name, country, info) 
     VALUES ('Band 1', 'Country1', 'Band description 1'),
            ('Band 2', 'Country2', 'Band description 2'),
            ('Band 3', 'Country3', 'Band description 3'),
            ('Band 4', 'Country4', 'Band description 4')
;

insert into Staff values
    ('01010101-0101','Name Name','Street1, City1'),
    ('02020202-0202','Name Name','Street2, City2')
;

insert into Stage values
    ('Mallorca', 250),
    ('Forum', 5000),
    ('Diesel', 750)
;

insert into in_charge (stage_name, start_time, stop_time) values
    ('Mallorca', '2019-06-20 12:00:00', '2019-06-20 17:59:59'),
	('Mallorca', '2019-06-20 18:00:00', '2019-06-20 23:59:59'),
	('Mallorca', '2019-06-21 00:00:00', '2019-06-21 05:59:59'),
	('Mallorca', '2019-06-21 12:00:00', '2019-06-21 17:59:59'),
	('Mallorca', '2019-06-21 18:00:00', '2019-06-21 23:59:59'),
	('Mallorca', '2019-06-22 00:00:00', '2019-06-22 05:59:59'),
	('Mallorca', '2019-06-22 12:00:00', '2019-06-22 17:59:59'),
	('Mallorca', '2019-06-22 18:00:00', '2019-06-22 23:59:59'),
	('Mallorca', '2019-06-23 00:00:00', '2019-06-23 05:59:59'),
	('Diesel', '2019-06-20 12:00:00', '2019-06-20 17:59:59'),
	('Diesel', '2019-06-20 18:00:00', '2019-06-20 23:59:59'),
	('Diesel', '2019-06-21 00:00:00', '2019-06-21 05:59:59'),
	('Diesel', '2019-06-21 12:00:00', '2019-06-21 17:59:59'),
	('Diesel', '2019-06-21 18:00:00', '2019-06-21 23:59:59'),
	('Diesel', '2019-06-22 00:00:00', '2019-06-22 05:59:59'),
	('Diesel', '2019-06-22 12:00:00', '2019-06-22 17:59:59'),
	('Diesel', '2019-06-22 18:00:00', '2019-06-22 23:59:59'),
	('Diesel', '2019-06-23 00:00:00', '2019-06-23 05:59:59'),
	('Forum', '2019-06-20 12:00:00', '2019-06-20 17:59:59'),
	('Forum', '2019-06-20 18:00:00', '2019-06-20 23:59:59'),
	('Forum', '2019-06-21 00:00:00', '2019-06-21 05:59:59'),
	('Forum', '2019-06-21 12:00:00', '2019-06-21 17:59:59'),
	('Forum', '2019-06-21 18:00:00', '2019-06-21 23:59:59'),
	('Forum', '2019-06-22 00:00:00', '2019-06-22 05:59:59'),
	('Forum', '2019-06-22 12:00:00', '2019-06-22 17:59:59'),
	('Forum', '2019-06-22 18:00:00', '2019-06-22 23:59:59'),
	('Forum', '2019-06-23 00:00:00', '2019-06-23 05:59:59')
;

INSERT INTO Member (email, name) VALUES
    ('person1@mail.com', 'Member 1'),
    ('person2@mail.com', 'Member 2'),
    ('person3@mail.com', 'Member 3'),
    ('person4@mail.com', 'Member 4'),
    ('person5@mail.com', 'Member 5'),
    ('person6@mail.com', 'Member 6'),
    ('person7@mail.com', 'Member 7'),
    ('person8@mail.com', 'Member 8')
;

INSERT INTO Plays_in VALUES
    ('Band 1', 'person1@mail.com'),
    ('Band 1', 'person2@mail.com'),
    ('Band 2', 'person3@mail.com'),
    ('Band 2', 'person4@mail.com'),
    ('Band 3', 'person5@mail.com'),
    ('Band 3', 'person6@mail.com'),
    ('Band 4', 'person7@mail.com'),
    ('Band 4', 'person8@mail.com')
;

--start stop stage band
INSERT INTO Concert VALUES
    ('2019-06-20 13:00:00', '2019-06-20 15:00:00', 'Forum', 'Band 1'),
    ('2019-06-20 17:00:00', '2019-06-20 19:00:00', 'Forum', 'Band 2'),
    ('2019-06-20 21:00:00', '2019-06-20 23:00:00', 'Forum', 'Band 3'),

    ('2019-06-20 14:00:00', '2019-06-20 16:00:00', 'Mallorca', 'Band 1'),
    ('2019-06-20 18:00:00', '2019-06-20 20:00:00', 'Mallorca', 'Band 4'),
    ('2019-06-20 22:00:00', '2019-06-21 00:00:00', 'Mallorca', 'Band 3'),

    ('2019-06-20 13:00:00', '2019-06-20 17:00:00', 'Diesel', 'Band 3'),
    ('2019-06-20 17:00:00', '2019-06-20 19:00:00', 'Diesel', 'Band 2'),
    ('2019-06-20 21:00:00', '2019-06-20 23:00:00', 'Diesel', 'Band 1'),

    ('2019-06-21 13:00:00', '2019-06-21 15:00:00', 'Forum', 'Band 1'),
    ('2019-06-21 17:00:00', '2019-06-21 19:00:00', 'Forum', 'Band 2'),
    ('2019-06-21 21:00:00', '2019-06-21 23:00:00', 'Forum', 'Band 4'),

    ('2019-06-21 14:00:00', '2019-06-21 16:00:00', 'Mallorca', 'Band 4'),
    ('2019-06-21 18:00:00', '2019-06-21 20:00:00', 'Mallorca', 'Band 2'),
    ('2019-06-21 22:00:00', '2019-06-22 00:00:00', 'Mallorca', 'Band 3'),

    ('2019-06-21 13:00:00', '2019-06-21 17:00:00', 'Diesel', 'Band 1'),
    ('2019-06-21 17:00:00', '2019-06-21 19:00:00', 'Diesel', 'Band 3'),
    ('2019-06-21 21:00:00', '2019-06-21 23:00:00', 'Diesel', 'Band 2'),

    ('2019-06-22 13:00:00', '2019-06-22 15:00:00', 'Forum', 'Band 4'),
    ('2019-06-22 17:00:00', '2019-06-22 19:00:00', 'Forum', 'Band 2'),
    ('2019-06-22 21:00:00', '2019-06-22 23:00:00', 'Forum', 'Band 3'),

    ('2019-06-22 14:00:00', '2019-06-22 16:00:00', 'Mallorca', 'Band 1'),
    ('2019-06-22 18:00:00', '2019-06-22 20:00:00', 'Mallorca', 'Band 3'),
    ('2019-06-22 22:00:00', '2019-06-23 00:00:00', 'Mallorca', 'Band 2')
;

UPDATE In_charge SET staff_pno = '01010101-0101' WHERE stage_name = 'Mallorca' AND start_time = '2019-06-20 12:00:00'; 
UPDATE In_charge SET staff_pno = '02020202-0202' WHERE stage_name = 'Forum' AND start_time = '2019-06-20 18:00:00'; 
UPDATE In_charge SET staff_pno = '01010101-0101' WHERE stage_name = 'Forum' AND start_time = '2019-06-21 00:00:00'; 
UPDATE In_charge SET staff_pno = '02020202-0202' WHERE stage_name = 'Mallorca' AND start_time = '2019-06-21 12:00:00'; 
UPDATE In_charge SET staff_pno = '01010101-0101' WHERE stage_name = 'Mallorca' AND start_time = '2019-06-21 18:00:00'; 

UPDATE band SET contact_person = '01010101-0101' WHERE name = 'Band 1';
UPDATE band SET contact_person = '01010101-0101' WHERE name = 'Band 2';
UPDATE band SET contact_person = '02020202-0202' WHERE name = 'Band 3';
UPDATE band SET contact_person = '02020202-0202' WHERE name = 'Band 4';


