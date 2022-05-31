DROP TABLE IF EXISTS airline CASCADE;
DROP TABLE IF EXISTS airplane CASCADE;
DROP TABLE IF EXISTS flight CASCADE;
DROP TABLE IF EXISTS passenger CASCADE;
DROP TABLE IF EXISTS schedule CASCADE;
DROP TABLE IF EXISTS ticket CASCADE;
DROP TABLE IF EXISTS ticket_passenger CASCADE;


CREATE TABLE airline
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    phone VARCHAR(64) NOT NULL
);

CREATE TABLE airplane
(
    id SERIAL PRIMARY KEY,
    model VARCHAR(64) NOT NULL,
    license_plate VARCHAR(30) NOT NULL,
    airline_id INTEGER NOT NULL
        CONSTRAINT FK_airline REFERENCES airline (id)
            ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE flight
(
    id SERIAL PRIMARY KEY,
    "from" VARCHAR(64) NOT NULL,
    "to" VARCHAR(64) NOT NULL,
    departure_time TIMESTAMP NOT NULL,
    arrival_time TIMESTAMP NOT NULL,
    airplane_id INTEGER
        CONSTRAINT FK_airplane REFERENCES airplane (id)
            ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE passenger
(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(64) NOT NULL,
    last_name VARCHAR(64) NOT NULL,
    phone VARCHAR(64) NOT NULL
);

CREATE TABLE ticket
(
    id SERIAL PRIMARY KEY,
    fare NUMERIC(10, 2) NOT NULL,
    currency VARCHAR(64) NOT NULL,
    expired BOOLEAN NOT NULL,
    flight_id INTEGER NOT NULL
        CONSTRAINT FK_flight REFERENCES flight (id)
            ON DELETE CASCADE ON UPDATE CASCADE,
    create_at TIMESTAMP NOT NULL,
    update_at TIMESTAMP
);

CREATE TABLE ticket_passenger
(
    ticket_id INTEGER NOT NULL
        CONSTRAINT FK_ticket REFERENCES ticket (id)
            ON DELETE CASCADE ON UPDATE CASCADE,
    passenger_id INTEGER NOT NULL
        CONSTRAINT FK_passenger REFERENCES passenger (id)
            ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (ticket_id, passenger_id)
);


INSERT INTO airline(name, phone)
VALUES
    ('American Airlines', '800-433-7300'),
    ('Delta', '800-221-1212'),
    ('Emirates', '1 800 777 3999');

INSERT INTO airplane(model, license_plate, airline_id)
VALUES
    ('Airbus A350', '', 1),
    ('Boeing 767', '', 2),
    ('Airbus A220', '', 3),
    ('Airbus A350', '', 2),
    ('Boeing 787 Dreamliner', '', 1),
    ('Airbus A320neo', '', 1);

INSERT INTO passenger(first_name, last_name, phone)
VALUES
    ('John', 'Smith', '802-245-4047'),
    ('James', 'Johnson', '925-779-3393'),
    ('Alex', 'Williams', '831-772-1486'),
    ('Robert', 'Brown', '484-839-4969');

INSERT INTO flight("from", "to", departure_time, arrival_time, airplane_id)
VALUES
    ('Mexico', 'France','2015-01-01:18:50:00'::timestamp, '2015-01-02:10:20:00'::timestamp, 2),
    ('Texas', 'London', '2015-03-25:02:10:00'::timestamp, '2015-03-25:21:30:00'::timestamp, 1),
    ('London', 'Texas','2015-02-12:09:30:00'::timestamp, '2015-02-13:00:20:00'::timestamp, 2),
    ('Kansas', 'Canberra','2015-01-06:10:45:00'::timestamp, '2015-01-06:18:15:00'::timestamp, 1),
    ('Georgia', 'Kraków', '2015-03-09:23:45:00'::timestamp, '2015-03-10:6:05:00'::timestamp, 2),
    ('Kitakyushu', 'Cologne', '2015-02-19:15:50:00'::timestamp, '2015-02-20:08:20:00'::timestamp, 3);

INSERT INTO ticket(fare, currency, expired, flight_id, create_at)
VALUES
    (250.99, 'USD ', FALSE, 2, now()),
    (220.99, 'USD ', FALSE, 1, now()),
    (170.99, 'EUR ', FALSE, 4, now()),
    (315.99, 'USD ', FALSE, 2, now()),
    (289.99, 'USD ', FALSE, 3, now()),
    (42000.99, 'JPY  ', FALSE, 6, now());

INSERT INTO ticket_passenger(ticket_id, passenger_id)
VALUES
    (3, 2),
    (5, 3),
    (2, 1),
    (1, 4),
    (1, 1),
    (6, 2);