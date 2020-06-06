create database Inventory;
use `Inventory`;

create table `product`(
`id` int auto_increment not null , 
`name` varchar(45) not null,
`category` varchar(45) not null,
`price` int not null,
primary key(id));


create table `order`(
`id` int  auto_increment not null,
`date` date not null,
primary key(id));

create table `order_details`(
`id` int auto_increment not null,
`order_id` int not null,
`product_id` int not null,
`quantity` int not null,
`price` int not null,
primary key(id),
foreign key(`order_id`) references `order`(id),
foreign key(`product_id`) references `product`(id)); 