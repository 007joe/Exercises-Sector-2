DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS interest_group;
DROP TABLE IF EXISTS event_members;
DROP TABLE IF EXISTS event;
DROP TABLE IF EXISTS interest_members_group;

CREATE TABLE member (
member_id SERIAL,
last_name VARCHAR(100) NOT NULL,
first_name VARCHAR (100) NOT NULL,
email_address VARCHAR(130) NOT NULL,
phone_number VARCHAR(20),
birth_date date NOT NULL,
reminder_email boolean NOT NULL,

CONSTRAINT PK_member PRIMARY KEY (member_id)
);

CREATE TABLE interest_group (
group_id SERIAL,
name VARCHAR(100) NOT NULL,

CONSTRAINT PK_interest_group PRIMARY KEY (group_id),
CONSTRAINT UQ_interest_group_name UNIQUE (name)
);

CREATE TABLE event (
event_id SERIAL,
name VARCHAR(100) NOT NULL,
description TEXT NOT NULL,
start_date DATE NOT NULL,
start_time VARCHAR(100) NOT NULL,
duration int NOT NULL,
group_id int NOT NULL,

CONSTRAINT pk_event PRIMARY KEY (event_id),
CONSTRAINT CHK__DURATION CHECK (duration >= 30),
CONSTRAINT FK_event_interest_id FOREIGN KEY (group_id) REFERENCES interest_group(group_id)
);

CREATE TABLE event_members (
event_id INT NOT NULL,
member_id INT NOT NULL,

CONSTRAINT PK_event_members PRIMARY KEY (event_id),
CONSTRAINT FK_event_members_event FOREIGN KEY (event_id) REFERENCES event(event_id),
CONSTRAINT FK_event_members_members FOREIGN KEY (member_id) REFERENCES member (member_id)
);

CREATE TABLE interest_members_group(
group_id INT NOT NULL,
 member_id INT NOT NULL,

 CONSTRAINT PK_interest_members_group PRIMARY KEY (group_id),
 CONSTRAINT FK_interest_members_group_group FOREIGN KEY (group_id) REFERENCES interest_group (group_id),
 CONSTRAINT FK_interest_members_group_member FOREIGN KEY (member_id) REFERENCES member (member_id)
);
--EVENTS
INSERT INTO event(name, description, start_date, start_time, duration, group_id)
VALUES('UFC Fight', 'Anderson Silva vs Jon Jones', '2-23-2022', '12:00 AM', 60, 1);

INSERT INTO event(name, description, start_date, start_time, duration, group_id)
VALUES('6ers Game', 'James Harden Debut', '2-25-2022', '7:00 PM', 120, 2);

INSERT INTO event(name, description, start_date, start_time, duration, group_id)
VALUES('UFC fight', 'Khabib Nurmagomedeov vs Georges St Pierre', '2-30-2022', '10:00 PM', 60, 3);

INSERT INTO event(name, description, start_date, start_time, duration, group_id)
VALUES('SuperBowl', 'Bengals vs Rams', '2-14-2022', '6:00 PM', 180, 4);

--INTEREST GROUPS
INSERT INTO interest_group (name)
VALUES('UFC');
INSERT INTO interest_group (name)
VALUES('Football');
INSERT INTO interest_group (name)
VALUES('Basketball');


--MEMBERS
INSERT INTO member(last_name, first_name, email_address, birth_date, reminder_email)
VALUES('Simmons', 'Ben', 'BS@gmail.com', '1995-01-20', true);
INSERT INTO member(last_name, first_name, email_address, birth_date, reminder_email)
VALUES('Johnson', 'Earl', 'EJ@gmail.com', '1995-01-20', false);
INSERT INTO member(last_name, first_name, email_address, birth_date, reminder_email)
VALUES('Williams', 'Jacob', 'JW@gmail.com', '1995-01-20', true);
INSERT INTO member(last_name, first_name, email_address, birth_date, reminder_email)
VALUES('Stevens', 'Chris', 'CS@gmail.com', '1995-01-20', false);
INSERT INTO member(last_name, first_name, email_address, birth_date, reminder_email)
VALUES('Smith', 'Kevin', 'KS@gmail.com', '1995-01-20', true);
INSERT INTO member(last_name, first_name, email_address, birth_date, reminder_email)
VALUES('Michaels', 'Shawn', 'SM@gmail.com', '1995-01-20', false);
INSERT INTO member(last_name, first_name, email_address, birth_date, reminder_email)
VALUES('Saget', 'Bob', 'BS@gmail.com', '1995-01-20', true);
INSERT INTO member(last_name, first_name, email_address, birth_date, reminder_email)
VALUES('Turner', 'Evan', 'ET@gmail.com', '1995-01-20', false);

--INTEREST GROUP MEMBERS
INSERT INTO interest_members_group(group_id, member_id)
VALUES(1, 1);
INSERT INTO interest_members_group(group_id, member_id)
VALUES(1, 2);
INSERT INTO interest_members_group(group_id, member_id)
VALUES(2, 3);
INSERT INTO interest_members_group(group_id, member_id)
VALUES(3, 4);
INSERT INTO interest_members_group(group_id, member_id)
VALUES(3, 5);
INSERT INTO interest_members_group(group_id, member_id)
VALUES(2, 6);
INSERT INTO interest_members_group(group_id, member_id)
VALUES(1, 7);
INSERT INTO interest_members_group(group_id, member_id)
VALUES(3, 8);

--EVENT MEMBERS
INSERT INTO event_members(event_id, member_id)
VALUES(1, 1);
INSERT INTO event_members(event_id, member_id)
VALUES(1, 2);
INSERT INTO event_members(event_id, member_id)
VALUES(2, 3);
INSERT INTO event_members(event_id, member_id)
VALUES(3, 4);
INSERT INTO event_members(event_id, member_id)
VALUES(3, 5);
INSERT INTO event_members(event_id, member_id)
VALUES(2, 6);
INSERT INTO event_members(event_id, member_id)
VALUES(1, 7);
INSERT INTO event_members(event_id, member_id)
VALUES(3, 8);



