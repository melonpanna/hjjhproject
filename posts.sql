-- 유저마다 'category' 존재
-- categoryId를 통하여 post 구분

-- -----------------------------------------------------
-- Table `posts`
-- -----------------------------------------------------

create table posts(
 id bigint(19) not null auto_increment,
 name varchar(255) not null,
 userId varchar(20) not null,
 title char(30) not null,
 regDate date not null,
 contents longtext not null,
 categoryId bigint(19) not null,
 primary key (id)
);



-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------

create table category(
 id bigint(19) not null auto_increment,
 userId varchar(20) not null,
 post_count int(11),
 public_post_count int(11),
 
 primary key(id)
);
