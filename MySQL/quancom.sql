create database quancom;
use quancom;

CREATE TABLE functions(
	func_id INT NOT NULL auto_increment,
	func_name varchar(50),
	description varchar(50),
	PRIMARY KEY(func_id)
);

CREATE TABLE roles(
	role_id INT NOT NULL auto_increment,
    role_name varchar(50),
    description varchar(50),
    PRIMARY KEY(role_id)
);

CREATE TABLE role_func(
	role_id INT NOT NULL,
	func_id INT NOT NULL,
	PRIMARY KEY(role_id,func_id),
	FOREIGN KEY (func_id) REFERENCES functions(func_id),
	FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

CREATE TABLE category(
	cate_id INT NOT NULL auto_increment,
    cate_name varchar(50),
    description varchar(50),
    PRIMARY KEY(cate_id)
);

CREATE TABLE users(
	user_id INT NOT NULL auto_increment,  
	username varchar(20),
    password varchar(50),
	email varchar(50),
    role_id INT NOT NULL,
    foreign key(role_id) REFERENCES roles(role_id),
    PRIMARY KEY(user_id)
    
);
alter table users add staff_id int;
ALTER TABLE users ADD CONSTRAINT FOREIGN KEY (staff_id) REFERENCES staffs(staff_id);

CREATE TABLE staffs(
	staff_id INT NOT NULL auto_increment,
    name nvarchar(50),
    date_of_birth datetime,
    gender varchar(4),
    address nvarchar(50),
	phonenumber varchar(50),
	primary key(staff_id),
    image varchar(100)
);
CREATE TABLE orders(
	order_id INT NOT NULL auto_increment,
    order_status varchar(10) constraint check(order_status = 'Incomplete' or order_status = 'Complete'),
	order_date datetime,
    tonggia int,
    user_id int,
	staff_id int,
    PRIMARY KEY(order_id),
    FOREIGN KEY(user_id) REFERENCES users(user_id),
	FOREIGN KEY(staff_id) REFERENCES staffs(staff_id)
);

INSERT INTO functions(func_name) VALUES
('Đơn hàng'),
('Nhập hàng'),
('Món ăn'),
('Nguyên liệu'),
('Tài khoản'),
('Nhân viên'),
('Nhà cung cấp'),
('Thống kê');

CREATE TABLE products(
	product_id INT NOT NULL auto_increment,
    product_name varchar(50),
    soluong int,
	donvitinh varchar(15),
	gia int,
    category_id int,
	image varchar(100),
    PRIMARY KEY(product_id),
    FOREIGN KEY(category_id) REFERENCES category(cate_id)
);

CREATE TABLE chitiet_orders(
	order_id INT NOT NULL auto_increment,
    product_id int,
	name nvarchar(50),
	soluong int,
	gia int,	
    PRIMARY KEY(order_id, product_id),
    FOREIGN KEY(order_id) REFERENCES orders(order_id)
);                    

CREATE TABLE supplier(
	sup_id int not null auto_increment,
	sup_name varchar(50),
	sup_address varchar(50),
    supp_phone varchar(10),
	PRIMARY KEY(sup_id)
);

CREATE TABLE phieuNhap(
	phieu_id INT NOT NULL auto_increment,
    staff_id int,
    sup_id int,
	tonggia int,
	date datetime,
	PRIMARY KEY(phieu_id),
	FOREIGN KEY(staff_id) REFERENCES staffs(staff_id),
	FOREIGN KEY(sup_id) REFERENCES supplier(sup_id)
);


CREATE TABLE materials(
	material_id INT NOT NULL auto_increment,
    name nvarchar(50),
	donvitinh nvarchar(20),
	gia int,
    soluong int,
	PRIMARY KEY(material_id)
);
CREATE TABLE chitietphieuNhap(
	material_id INT NOT NULL,
    phieu_id int,
    name nvarchar(50),
	soluong int,
	gia int,
	PRIMARY KEY(material_id, phieu_id),
	FOREIGN KEY(material_id) REFERENCES materials(material_id),
	FOREIGN KEY(phieu_id) REFERENCES phieuNhap(phieu_id)
);

create table chitietcongthuc(
	product_id int,
	material_id int,
	name_material varchar(50),
	gia int,
	soluong int,
	PRIMARY KEY(product_id, material_id),
	FOREIGN KEY(material_id) REFERENCES materials(material_id),
    FOREIGN KEY(product_id) REFERENCES products(product_id)
);


use quancom;
select * from staffs
where phonenumber like '%043%'
order by name asc;
select * from roles;
select * from functions;
select * from role_func;
select * from users;
select * from supplier;
select * from materials;
select * from category;
select * from phieuNhap where tonggia <= 20000 and tonggia >= 5000;
select user_id, username, role_name from users as u join roles as r on u.role_id = r.role_id and u.username like '%ba%' order by r.role_name asc;

select product_id, product_name, soluong, donvitinh, c.cate_id, cate_name from products as p join category as c on p.category_id=c.cate_id  and cate_name='cơm gà';

select * from phieunhap where date between '2023-05-05' and '2023-05-08';

select * from orders;
select * from chitiet_orders;


select * from products;
select * from staffs;

SELECT o.order_id,
	o.order_date,
	o.tonggia,
	s.name
FROM orders as o  
join staffs as s
 ON  s.staff_id=o.staff_id and o.order_id=10