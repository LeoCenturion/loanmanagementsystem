CREATE SCHEMA IF NOT EXISTS MAIN;
CREATE TABLE IF NOT EXISTS MAIN.loans
(
    id         int4 NOT NULL,
    amount     int4 NOT NULL,
    borrowerId int4 NOT NULL,
    CONSTRAINT loans_pk PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS MAIN.persons
(
    id        int4 NOT NULL,
    lastName  varchar,
    firstName varchar,
    email     varchar,
    CONSTRAINT persons_pk PRIMARY KEY (id)
);
CREATE TABLE main.registrations
(
    personid       integer NULL,
    "user"         varchar NULL,
    "password"     varchar NULL,
    registrationid integer NULL,
    CONSTRAINT registrations_pk PRIMARY KEY (registrationid),
    CONSTRAINT registrations_fk FOREIGN KEY (personid) REFERENCES main.persons (id)
);
