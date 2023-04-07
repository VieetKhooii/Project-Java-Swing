create database quancom;
use quancom;

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

CREATE TABLE role_func(
	role_id INT NOT NULL,
	func_id INT NOT NULL,
	PRIMARY KEY(role_id,func_id),
	FOREIGN KEY (func_id) REFERENCES functions(func_id),
	FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

CREATE TABLE mutual_category(
	mutual_id INT NOT NULL,
    mutual_name varchar(50),
    description varchar(50),
    PRIMARY KEY(mutual_id)
);

CREATE TABLE category(
	cate_id INT NOT NULL,
    cate_name varchar(50),
    description varchar(50),
    mutual_id int,
    PRIMARY KEY(cate_id),
    FOREIGN KEY(mutual_id) REFERENCES mutual_category(mutual_id)
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
    address nvarchar(50),
	phonenumber varchar(50),
	primary key(staff_id)
);
CREATE TABLE orders(
	order_id INT NOT NULL,
    order_status varchar(50),
	order_date datetime,
    tonggia int,
    user_id int,
	staff_id int,
    PRIMARY KEY(order_id),
    FOREIGN KEY(user_id) REFERENCES users(user_id),
	FOREIGN KEY(staff_id) REFERENCES staffs(staff_id)
);
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
	phieu_id INT NOT NULL,
    staff_id int,
    sup_id int,
	tonggia int,
	date datetime,
	PRIMARY KEY(phieu_id),
	FOREIGN KEY(staff_id) REFERENCES staffs(staff_id),
	FOREIGN KEY(sup_id) REFERENCES supplier(sup_id)
);


CREATE TABLE materials(
	material_id INT NOT NULL,
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
