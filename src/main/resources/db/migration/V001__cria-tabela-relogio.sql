create table relogio (
       id bigint generated by default as identity,
        data_cadastro date,
        marca varchar(50) not null,
        status varchar(255),
        primary key (id)
    )