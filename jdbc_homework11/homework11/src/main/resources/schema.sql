CREATE SCHEMA homework11_database;

CREATE TABLE IF NOT EXISTS Director (
    id bigint auto_increment primary key,
    first_name varchar(255),
    last_name varchar(255),
    age int
);

CREATE TABLE IF NOT EXISTS Company (
    id bigint auto_increment primary key,
    name varchar(255),
    employeeQuantity int,
    companyPrice int
);

CREATE TABLE IF NOT EXISTS DirectorOfCompany (
    Director_id bigint not null,
    Company_id bigint not null,
    primary key (Director_id, Company_id),
    foreign key (Director_id) references Director(id),
    foreign key (Company_id) references Company(id)
);