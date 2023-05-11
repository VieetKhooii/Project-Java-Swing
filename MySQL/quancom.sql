create database quancom;
use quancom;

select p.product_name, m.name, ct.soluong
from products p join chitietcongthuc ct on p.product_id = ct.product_id
	 join materials m on m.material_id = ct.material_id
order by p.product_name;

SELECT SUM(soluong), product_id
FROM chitiet_orders 
WHERE product_id = 1;

SELECT s.staff_id, s.name, SUM(ct.soluong) as total_amount 
FROM chitiet_orders ct 
INNER JOIN orders o ON ct.order_id = ct.order_id 
INNER JOIN staffs s ON o.staff_id = s.staff_id 
GROUP BY s.staff_id, s.name;

SELECT COALESCE(SUM(ct.gia), 0) as total_price
FROM staffs s 
LEFT JOIN orders o ON s.staff_id = o.staff_id 
LEFT JOIN chitiet_orders ct ON o.order_id = ct.order_id 
WHERE s.staff_id = 2
GROUP BY s.staff_id, s.name;

SELECT COALESCE(SUM(ct.gia), 0) as total_price
FROM supplier s 
LEFT JOIN phieuNhap pn ON s.sup_id = pn.sup_id
LEFT JOIN chitietphieuNhap ct ON pn.phieu_id = ct.phieu_id 
WHERE s.sup_id = 2
GROUP BY s.staff_id, s.name;

SELECT COALESCE(SUM(ct.soluong), 0) as total_amount
FROM materials m 
LEFT JOIN chitietphieuNhap ct ON m.material_id = ct.material_id
WHERE m.material_id = 51;

SELECT COALESCE(SUM(ct.gia), 0) as total_price
FROM materials m 
LEFT JOIN chitietphieuNhap ct ON m.material_id = ct.material_id
WHERE m.material_id = 51;
select * from chitietphieuNhap;
SELECT s.staff_id, s.name, SUM(ct.soluong) as total_amount 
FROM chitiet_orders ct 
INNER JOIN orders o ON ct.order_id = ct.order_id 
INNER JOIN staffs s ON o.staff_id = s.staff_id 
GROUP BY s.staff_id, s.name;

select count(*) as count from orders where staff_id = 4;
select count(*) as count from phieuNhap where staff_id = 2;
CREATE TABLE functions(
	func_id INT NOT NULL,
	func_name varchar(50),
	description varchar(50),
	PRIMARY KEY(func_id)
);

CREATE TABLE roles(
	role_id INT NOT NULL,
    role_name varchar(50),
    description varchar(50),
    PRIMARY KEY(role_id)
);
select * from orders;
CREATE TABLE role_func(
	role_id INT NOT NULL,
	func_id INT NOT NULL,
	PRIMARY KEY(role_id,func_id),
	FOREIGN KEY (func_id) REFERENCES functions(func_id),
	FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

CREATE TABLE category(
	cate_id INT NOT NULL,
    cate_name varchar(50),
    description varchar(50)
);


CREATE TABLE users(
	user_id INT NOT NULL,  
	username varchar(20),
    password varchar(50),
    fullname varchar(50),
	email varchar(50),
	address varchar(50),
	phonenumber char(10), 
    role_id INT NOT NULL,
    foreign key(role_id) REFERENCES roles(role_id),
    PRIMARY KEY(user_id)
);
CREATE TABLE staffs(
	staff_id INT NOT NULL,
    name nvarchar(50),
    date_of_birth datetime,
    gender varchar(4),
    address nvarchar(50),
	phonenumber varchar(50),
	primary key(staff_id)
);

CREATE TABLE orders(
	order_id INT NOT NULL,
    order_status varchar(10) constraint check(order_status = 'Incomplete' or order_status = 'Complete'),
	order_date datetime,
    tonggia int,
    user_id int,
	staff_id int,
    PRIMARY KEY(order_id),
    FOREIGN KEY(user_id) REFERENCES users(user_id),
	FOREIGN KEY(staff_id) REFERENCES staffs(staff_id)
);
select * from category;
select * from materials;
select * from roles;
select * from role_func order by role_id asc;
select * from functions;
select * from users;
select * from orders;
select * from supplier;
select * from staffs;
select * from phieuNhap;
select * from chitietphieuNhap;
select * from products;
select * from chitietcongthuc;
select * from chitiet_orders;

INSERT INTO functions(func_name) VALUES
('Đơn hàng'),
('Nhập hàng'),
('Món ăn'),
('Nguyên liệu'),
('Tài khoản'),
('Nhân viên'),
('Nhà cung cấp');

CREATE TABLE products(
	product_id INT NOT NULL,
    product_name varchar(50),
    soluong int,
	donvitinh varchar(15),
	gia int,
    category_id int,
    PRIMARY KEY(product_id),
    FOREIGN KEY(category_id) REFERENCES category(cate_id)
);
CREATE TABLE chitiet_orders(
	order_id INT NOT NULL,
    product_id int,
	name nvarchar(50),
	soluong int,
	gia int,
	
    PRIMARY KEY(order_id, product_id),
    FOREIGN KEY(order_id) REFERENCES orders(order_id)
);                    


CREATE TABLE supplier(
	sup_id int not null,
	sup_name varchar(50),
	sup_address varchar(50),
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
	donvitnh nvarchar(20),
	gia int,
	category_id int,
	PRIMARY KEY(material_id),
	FOREIGN KEY(category_id) REFERENCES category(cate_id)
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
