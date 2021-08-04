create table if not exists Ordert(
	order_number varchar(45) primary key,
    user_id varchar(20),
    foreign key(user_id) references User(user_id),
    istotal_paid tinyint not null,
    total_price int not null
);
drop table Product;
create table if not exists Order_item(
	order_number varchar(45) primary key,
    pdt_code varchar(30),
    foreign key(pdt_code) references Product(pdt_code),
    isdelivery tinyint not null,
    ispaid tinyint not null
);
create table if not exists User(
	user_id varchar(20) primary key,
    user_name varchar(15) not null,
    user_addr varchar(100) not null,
    user_phone varchar(15) not null,
    user_phone2 varchar(15)
);
create table if not exists Product(
	pdt_code varchar(30) primary key,
    pdt_name varchar(45) not null,
    price int not null,
    quantity int not null
);