
CREATE TABLE `member` (
  `id` varchar(15),
  `password` varchar(20),
  `name` varchar(30),
  `phone` varchar(15),
  `address` varchar(50),
  `zipcode` int,
  PRIMARY KEY (`id`)
);
insert into ssafydb.member(id,password,name,phone,address1,zipcode)
values ('ssafy','ssafy','고길동','123-456-7894','서울시 중구','11000');
commit;
select * from ssafydb.member;