
CREATE SCHEMA IF NOT EXISTS MAIN;
CREATE SEQUENCE MAIN.HIBERNATE_SEQUENCE START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS MAIN.loans
(
    id         int4 NOT NULL,
    amount     int4 NOT NULL,
    borrowerId int4 NOT NULL,
    CONSTRAINT loans_pk PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS MAIN.persons
(
    id        int4 NOT NULL auto_increment,
    last_name  varchar,
    first_name varchar,
    email     varchar,
    CONSTRAINT persons_pk PRIMARY KEY (id)
);
CREATE TABLE main.registrations
(
    personId       integer NULL,
    "user"         varchar NULL,
    "password"     varchar NULL,
    CONSTRAINT registrations_pk PRIMARY KEY ("user"),
    CONSTRAINT registrations_fk FOREIGN KEY (personId) REFERENCES main.persons (id)
);
