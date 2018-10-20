create table pokemon
(
    id integer not null AUTO_INCREMENT,
    searchName varchar(255) not null,
    name varchar(255) not null,
    ability varchar(255) not null,
    heldItem varchar(255) not null,
    nature varchar(255) not null,
    attack integer,
    defence integer,
    specialAttack integer,
    specialDefence integer,
    hitPoints integer,
    speed integer,
    move1 varchar(255) not null,
    move2 varchar(255) not null,
    move3 varchar(255) not null,
    move4 varchar(255) not null,
    primary key(id)
);