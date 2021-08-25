-- 그냥작성함일단

-- -----------------------------------------------------
-- Table `member`
-- -----------------------------------------------------
create table member(
                       id bigint(19) not null auto_increment,
                       name varchar(255) not null,
                       userId varchar(20) not null,
                       email varchar(25) not null,
                       password char(60) not null,
                       birth date not null,
                       auth varchar(20) not null,
                       primary key (id)
);

