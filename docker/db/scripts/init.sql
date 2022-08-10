DROP TABLE IF EXISTS tb_user;
DROP TABLE IF EXISTS tb_user_profile;
DROP TABLE IF EXISTS tb_user_top_five;

CREATE SCHEMA IF NOT EXISTS priv;

GRANT ALL ON SCHEMA priv to tswizle;

create table priv.tb_user (
     id int generated always as identity not null,
     username varchar(50) not null,
     email varchar(255) not null,
     password varchar(255) not null,
     primary key(id)
);

create table public.tb_user_profile (
    id int generated always as identity not null,
    user_id int not null,
    first_name varchar(80) not null,
    last_name varchar(80) not null,
    birth_date date not null,
    picture varchar(155),
    biography varchar(350),
    primary key(id),
    constraint fk_user foreign key(user_id) references priv.tb_user(id)
);

create table public.tb_user_top_five (
    profile_id int not null,
    game_title varchar(100) not null,
    game_picture varchar(155) not null,
    constraint fk_profile foreign key(profile_id) references public.tb_user_profile(id)
);