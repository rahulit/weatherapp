create table citytemp(
    citytempid bigint identity(1,1) not null,
    name varchar(25) not null,
    averagetemp numeric(5,2) not null default 0,
    constraint pk_citytemp primary key(citytempid)
);