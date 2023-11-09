create table ticket_order
(
	id bigint auto_increment primary key,
	user_id bigint not null,
	flight varchar(10) not null,
	class_type varchar(20) not null,
	contact_mobile varchar(15) not null,
	contact_name varchar(20) not null,
	status varchar(30) not null,
	created_at datetime not null
);

create table ticket_order_event
(
	id bigint auto_increment primary key,
	ticket_order_id bigint not null,
	status varchar(30) not null,
	created_at datetime not null
);

create table passenger
(
	id bigint auto_increment primary key,
	ticket_order_id bigint not null,
	ticket_no varchar(30) null,
	name varchar(20) not null,
	baggage_weight int null,
	mobile varchar(15) null,
	identification_number varchar(18) not null,
	age_type varchar(10) not null,
	price int not null,
	insurance_id varchar(36) null,
	insurance_name varchar(20) null,
	insurance_price int null
);

create table plane_ticket
(
	id bigint auto_increment primary key,
	ticket_no varchar(30) not null,
	e_ticket_no varchar(30) not null,
	sn int not null,
	passenger_id bigint not null,
	flight varchar(10) not null,
	destination varchar(20) not null,
	boarding_date date not null,
	seat_type varchar(20) not null,
	price int not null,
	seat varchar(20) null,
	gate varchar(20) null,
	boarding_time varchar(10) null
);

create table plane_ticket_event
(
	id bigint auto_increment primary key,
	plane_ticket_id varchar(30) not null,
	status varchar(30) not null,
	created_at datetime not null
);
