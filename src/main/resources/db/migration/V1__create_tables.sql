CREATE SEQUENCE seq_author start 1 increment 1;
CREATE SEQUENCE seq_book start 1 increment 1;
CREATE SEQUENCE seq_bookloan start 1 increment 1;
CREATE SEQUENCE seq_borrower start 1 increment 1;
CREATE SEQUENCE seq_genre start 1 increment 1;
CREATE SEQUENCE seq_publisher start 1 increment 1;

CREATE TABLE author
(
    id          bigint NOT NULL default nextval('seq_author'),
    author_name VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE book
(
    id             bigint       NOT NULL default nextval('seq_book'),
    available      boolean,
    description    VARCHAR(500),
    edition        int4,
    image_url      VARCHAR(100),
    isbn           VARCHAR(50),
    book_name      VARCHAR(100) NOT NULL,
    number_of_page int4,
    publish_date   DATE,
    rating         int4,
    author_id      int8,
    genre_id       int8,
    publisher_id   int8,
    PRIMARY KEY (id)
);

CREATE TABLE borrower
(
    id            bigint       NOT NULL default nextval('seq_borrower'),
    email         VARCHAR(25),
    borrower_name VARCHAR(100) NOT NULL,
    phone_number  VARCHAR(15),
    PRIMARY KEY (id)
);

CREATE TABLE genre
(
    id         bigint      NOT NULL default nextval('seq_genre'),
    genre_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE publisher
(
    id             bigint       NOT NULL default nextval('seq_publisher'),
    publisher_name VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE book_loan
(
    id               bigint NOT NULL default nextval('seq_bookloan'),
    date_of_arrival  DATE,
    date_of_purchase DATE,
    due_date         DATE,
    book_id          int8   NOT NULL,
    borrower_id      int8   NOT NULL,
    PRIMARY KEY (id)
);

