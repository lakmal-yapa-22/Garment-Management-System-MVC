create database star;
       use star;
CREATE TABLE Admin (
                       email VARCHAR(255) NOT NULL,
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       type VARCHAR(50)

);
CREATE TABLE AdminBuyer (
                       email VARCHAR(255) NOT NULL,
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       type VARCHAR(50)

);
CREATE TABLE Buyer (
                       buyer_id VARCHAR(20) PRIMARY KEY,
                       contact_number VARCHAR(20),
                       address TEXT,
                       email VARCHAR(100),
                       company_name VARCHAR(100)
);


CREATE TABLE Orders (
                        order_id varchar(35) primary key,
                        buyer_id VARCHAR(20) ,
                        date date not null,
                       time  time,

                       foreign key  (buyer_id) references  Buyer(buyer_id) on delete cascade on update cascade

);

CREATE TABLE payments (
                          payment_id varchar(35) primary key,
                          order_id varchar(35),
                          cashTendered DECIMAL,
                          balance DECIMAL,
                          cashier varchar(35),
                          paymentMethod varchar(35),
                          paymentStatus varchar(200),
                          FOREIGN KEY (order_id) REFERENCES Orders(order_id)
);

CREATE TABLE Product (
                         product_id VARCHAR(20) PRIMARY KEY,
                         qty INT,
                         price DECIMAL(10, 2),
                         product_type VARCHAR(100),
                         color varchar(30),
                        size varchar(10)
);



CREATE TABLE  OrderDetail (
                          order_id varchar(35) not null ,
                           product_id varchar(50) not null,
                           qty int not null,
                           unit_price double not null,
                            subTotal double not null,
                            discount_rate  varchar (10),
                           discount double not null,
                           finalAmount  double not null,

                   FOREIGN KEY (order_id) REFERENCES Orders(order_id) ON DELETE CASCADE ON UPDATE CASCADE,
                           FOREIGN KEY    (product_id) REFERENCES Product(product_id) ON DELETE CASCADE ON UPDATE CASCADE,
                             PRIMARY KEY (order_id, product_id)
);



CREATE TABLE RawMaterial (
                             invoice_number  VARCHAR(20) PRIMARY KEY,
                             location VARCHAR(100),
                             description TEXT,
                             qty INT
);


CREATE TABLE Supplier (
                          supplier_id VARCHAR(20) PRIMARY KEY,
                          supplier_name VARCHAR(100),
                          contact_number VARCHAR(20),
                          email VARCHAR(100),
                          address TEXT
);


CREATE TABLE RawMaterialDetail (
                                   invoice_number VARCHAR(20),
                                   supplier_id VARCHAR(20),
                                   FOREIGN KEY (invoice_number) REFERENCES RawMaterial(invoice_number) ON DELETE CASCADE ON UPDATE CASCADE,
                                   FOREIGN KEY (supplier_id) REFERENCES Supplier(supplier_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                   PRIMARY KEY (invoice_number, supplier_id)
);


CREATE TABLE Employee (
                          employee_id  VARCHAR(20) PRIMARY KEY,
                          employee_name VARCHAR(100),
                          contact_number VARCHAR(20),
                          salary text,

                          address TEXT,
                           birthday varchar(20),
                          product_id  VARCHAR(20),

                           FOREIGN KEY (product_id) REFERENCES Product(product_id)ON DELETE CASCADE ON UPDATE CASCADE


);




CREATE TABLE ProductDetail (
                               product_id VARCHAR(20),
                               invoice_number VARCHAR(20),
                               FOREIGN KEY (product_id) REFERENCES Product(product_id),
                               FOREIGN KEY (invoice_number) REFERENCES RawMaterial(invoice_number),
                               PRIMARY KEY (product_id, invoice_number)
);

INSERT INTO Admin (email, username, password, type)
VALUES ('lakmal2001yapa@gmail.com', 'star01', 'star123', 'superadmin');
INSERT INTO  AdminBuyer (email, username, password, type)
VALUES ('lakmal2001yapa@gmail.com', 'buyer01', 'buyer123', 'superadmin');

