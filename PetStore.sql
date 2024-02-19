CREATE DATABASE PetStore;
USE PetStore;
CREATE TABLE species (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50),
	status BOOLEAN
);
INSERT INTO species (name,status)
VALUES ('Dog',true),('Cat',true),('Turtle',true);
CREATE TABLE pet (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	speciesId INTEGER,
	breed VARCHAR(50),
	listPrice VARCHAR(50),
	salePercent INTEGER,
	taxIncluded VARCHAR(50),
	uploadDate DATE,
	age VARCHAR(50),
	gender VARCHAR(10),
	color VARCHAR(50),
	weight VARCHAR(50),
	country VARCHAR(50),
	description VARCHAR(1000),
	mainImage VARCHAR(255),
	isSold BOOLEAN,
	status BOOLEAN,
	CONSTRAINT FK_speciesId_Pet FOREIGN KEY (speciesId) REFERENCES species(id)
);
INSERT INTO pet (speciesId,breed,listPrice,salePercent,taxIncluded,uploadDate,age,gender,color,weight,country,description,mainImage,isSold,status)
VALUES (1, 'Beagle', '800$', 10, '720$', '2024-01-13', '2 years', 'Male', 'Golden', '30 kg', 'United States', 'Friendly and playful, good with kids.', 'Beaglemain.jpg', false, true),
(1, 'Husky', '500$', 20, '400$', '2024-01-12', '3 years', 'Female', 'White', '24 kg', 'Siberia', 'Elegant and affectionate, needs grooming.', 'Huskymain.jpg', false, true),
(1, 'Poodle', '200$', 0, '200$', '2024-01-11', '6 months', 'Male', 'Blue', '5 kg', 'Thailand', 'Colorful and low-maintenance.', 'Poodlemain.jpg', false, true),
(2, 'Birman', '15$', 0, '15$', '2024-01-10', '1 year', 'Female', 'Gray', '0.5 kg', 'United States', 'Small and active, suitable for small spaces.', 'Birmanmain.jpg', false, true),
(2, 'Bombay', '50$', 5, '47.5$', '2024-01-09', '8 months', 'Male', 'Brown', '1.5 kg', 'Netherlands', 'Compact size, friendly temperament.', 'Bombaymain.jpg', false, true);
CREATE TABLE petImage (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	petId INTEGER,
	imageUrl VARCHAR(255),
	status BOOLEAN,
	CONSTRAINT FK_petId_PetImage FOREIGN KEY (petId) REFERENCES pet(id)
);
INSERT INTO petImage (petId,imageUrl,status)
VALUES (1,'Beagle1.jpg',true),(1,'Beagle2.jpg',true),
(2,'Husky1.jpg',true),(2,'Husky2.jpg',true),
(3,'Poodle1.jpg',true),(3,'Poodle2.jpg',true),
(4,'Birman1.jpg',true),(4,'Birman2.jpg',true),
(5,'Bombay1.jpg',true),(5,'Bombay2.jpg',true);

CREATE TABLE user (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	fullName VARCHAR(50),
	email VARCHAR(50),
	birthday DATE,
	gender VARCHAR(10),
	createDate DATE,
	avatarUrl VARCHAR(255),
	phoneNumber VARCHAR(20),
	password VARCHAR(255),
	role VARCHAR(20),
	country VARCHAR(50),
	status BOOLEAN
);
INSERT INTO user (fullName,email,birthday,gender,createDate,avatarUrl,phoneNumber,password,role,country,status) 
VALUES ('Nguyen Anh Quoc','anhquoc11358@gmail.com','2000-04-13','male','2024-01-04','quoc.jpg','08041897785',
'$2a$12$A0wobbnw6HqCnMiOfo1ubO3CejCFavoEDBWmk0pTXE9hM0GIVNVCm','ROLE_ADMIN','Japan',true),
('To Thai Son','jsoibn@gmail.com','2002-01-01','male','2024-01-04','son.jpg','0123456789',
'$2a$12$1HS2eCxQuUQpRLz2xwKuWeIdNAUgIAQL/3/K/tsbPPdM8KDeFF1Qq','ROLE_CUSTOMER', 'Vietnam',true);
CREATE TABLE shipLocation (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	userId INTEGER,
	address VARCHAR(255),
	phoneNumber VARCHAR(20),
	isDefault BOOLEAN,
	status BOOLEAN,
	CONSTRAINT FK_userId_ShipLocation FOREIGN KEY (userId) REFERENCES user(id)
);
INSERT INTO shipLocation (userId, address, phoneNumber, isDefault, status)
VALUES
(1, '123 Main Street', '123-456-7890', true, true),
(1, '456 Oak Avenue', '987-654-3210', false, true),
(1, '789 Elm Lane', '555-123-4567', false, true),
(2, '321 Pine Road', '111-222-3333', true, true),
(2, '555 Maple Drive', '444-555-6666', false, true);

CREATE TABLE orders (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    userId INTEGER,
    shipLocationId INTEGER,
    total VARCHAR(50),
    payLink VARCHAR(255),
    code VARCHAR(50),
    orderTime DATETIME,
    isCompleted BOOLEAN,
    status BOOLEAN,
    CONSTRAINT FK_userId_Orders FOREIGN KEY (userId) REFERENCES user(id),
    CONSTRAINT FK_shipLocationId_Orders FOREIGN KEY (shipLocationId) REFERENCES shipLocation(id)
);
INSERT INTO orders (userId, shipLocationId, total, payLink, code, orderTime, isCompleted, status)
VALUES
(1, 1, '150$', 'https://payment-link-1.com', 'ABC123', '2024-01-13 10:30:00', false, true),
(1, 2, '80.5$', 'https://payment-link-2.com', 'DEF456', '2024-01-12 14:45:00', false, true),
(1, 3, '200.25$', 'https://payment-link-3.com', 'GHI789', '2024-01-11 20:00:00', false, true),
(2, 4, '45.75$', 'https://payment-link-4.com', 'JKL012', '2024-01-10 12:00:00', false, true),
(2, 5, '120.9$', 'https://payment-link-5.com', 'MNO345', '2024-01-09 18:30:00', false, true);

CREATE TABLE orderItem (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    petId INTEGER,
    orderId INTEGER,
    price VARCHAR(50),
    status BOOLEAN,
    CONSTRAINT FK_petId_OrderItem FOREIGN KEY (petId) REFERENCES pet(id),
    CONSTRAINT FK_orderId_OrderItem FOREIGN KEY (orderId) REFERENCES orders(id)
);
INSERT INTO orderItem (petId, orderId, price, status)
VALUES
(1, 1, '120$', true),
(2, 1, '30.5$', true),
(3, 2, '50.25$', true),
(4, 2, '15.75$', true),
(5, 3, '80.90$', true);

CREATE TABLE cart (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	userId INTEGER,
	status BOOLEAN,
	CONSTRAINT FK_userId_Cart FOREIGN KEY (userId) REFERENCES user(id)
);
INSERT INTO cart (userId, status)
VALUES(1, true),(2, true);

CREATE TABLE cartItem (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    petId INTEGER,
    cartId INTEGER,
    status BOOLEAN,
    CONSTRAINT FK_petId_CartItem FOREIGN KEY (petId) REFERENCES pet(id),
    CONSTRAINT FK_cartId_CartItem FOREIGN KEY (cartId) REFERENCES cart(id)
);
INSERT INTO cartItem (petId, cartId, status)
VALUES
(1, 1, true),
(5, 1, true),
(3, 2, true);


























