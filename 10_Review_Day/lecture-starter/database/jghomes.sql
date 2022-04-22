BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;
DROP TABLE IF EXISTS home;
DROP TABLE IF EXISTS address;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE address (
   addressId serial,
   addressLine varchar(64) not null,
   city varchar(50) not null,
   state varchar(50) not null,
   zip int not null,
   
   CONSTRAINT pk_address PRIMARY KEY (addressId) 

);




CREATE TABLE home (
  homeId serial,
  mlsNumber varchar(15) not null,
  addressId int not null,
  numberOfBathrooms int,
  numberOfBedrooms int,
  price decimal(12,2) not null,
  shortDescription varchar(255) not null,

  
  CONSTRAINT pk_home PRIMARY KEY (homeId),
  CONSTRAINT fk_home_address FOREIGN KEY (addressId) references address (addressId)

);



CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id),
	CONSTRAINT UQ_username UNIQUE (username)
);

-- SAMPLE DATA

--House 1
INSERT INTO address (addressId, addressLine, city, state, zip) VALUES (1, '123 Elm Street', 'Columbus', 'Ohio', 43323);
INSERT INTO home ( mlsNumber, addressId, numberOfBathrooms, numberOfBedrooms, price, shortDescription) values('MLS5123', 1, 4, 3, 30000.00,'Freddies Nightmare will come to your life in the classic home. Sweet dreams!');

-- UPDATING SEQUENCES SO THERE ARE NO CLASHES WHEN APP RUNS WITH EXISTING KEYS...
ALTER SEQUENCE address_addressid_seq RESTART WITH 100;
ALTER SEQUENCE home_homeid_seq RESTART WITH 100;

INSERT INTO users (user_id, username, password_hash)
VALUES (1001, 'jghomes', '$2a$10$69QwxtxIXZ323KL/yU4NTOAsbttJAJDloxp/XXvIPOA3.6ghlsLT2');

COMMIT TRANSACTION;
