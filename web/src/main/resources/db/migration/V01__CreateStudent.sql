create TABLE student
(
    id         bigint auto_increment PRIMARY KEY,
    name       VARCHAR(200) NOT NULL,
    birthday   DATE,
    created    TIMESTAMP,
    email      VARCHAR(60),
    gender     VARCHAR(20)  NOT NULL,
    height     DOUBLE       NOT NULL,
    weight     DOUBLE       NOT NULL,
    student_id VARCHAR(20)
);

create TABLE department
(
    id        bigint auto_increment PRIMARY KEY,
    dep_name  VARCHAR(60) NOT NULL,
    leader_id VARCHAR(60)
);

create Table address
(
    id   bigint auto_increment PRIMARY KEY,
    city VARCHAR(20),
    zip  VARCHAR(5),
    area VARCHAR(20),
    road VARCHAR(20)
);
create unique index on address(city,area,road);
create index on address(city);
create index on address(city,area);

create TABLE employee
(
    id            bigint auto_increment PRIMARY KEY,
    emp_id        VARCHAR(10),
    emp_name      VARCHAR(60) NOT NULL,
    birthday      DATE,
    created       TIMESTAMP,
    private_email VARCHAR(60),
    company_email VARCHAR(60),
    gender        VARCHAR(20) NOT NULL,
    address_id    INTEGER,
    address2      VARCHAR(50),
    dep_id        varchar(10),
    password      varchar(20)
);
create index on employee(emp_id);

create table users
(
    id             bigint auto_increment primary key not null,
    username       varchar(100) not null,
    hashedPassword varchar(200) not null,
    roles          varchar(400) not null,
    emp_id         varchar(10)
);
create unique index on users(username);
