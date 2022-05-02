DROP database IF EXISTS CarDealerShip;

CREATE database CarDealership;

USE CarDealership;

CREATE TABLE IF NOT EXISTS Car (
	CarId INT AUTO_INCREMENT PRIMARY KEY,
	Vin VARCHAR(17) NOT NULL,
    Make VARCHAR(20) NOT NULL,
    Model VARCHAR(20) NOT NULL,
    CarDescription VARCHAR(300),
    CarYear YEAR NOT NULL,
    SalePrice DECIMAL(15,2) NOT NULL,
    MSRP DECIMAL(15,2) NOT NULL,
    Color VARCHAR(20) NOT NULL,
    Interior VARCHAR(20) NOT NULL,
    BodyStyle VARCHAR(20) NOT NULL,
    Transmission VARCHAR(20) NOT NULL,
    Mileage INT NOT NULL,
    Used BOOLEAN NOT NULL,
    Sold BOOLEAN NOT NULL,
    ImageBinary MEDIUMBLOB 
);


CREATE TABLE IF NOT EXISTS Discount (
	DiscountId INT AUTO_INCREMENT PRIMARY KEY,
    Value Float NOT NULL,
    StartDate DATE,
    EndDate DATE
);

CREATE TABLE IF NOT EXISTS Permission (
	PermissionId INT PRIMARY KEY,
    PermissionName VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS Users (
	UserId INT AUTO_INCREMENT PRIMARY KEY,
    Password VARCHAR(225) NOT NULL,
    Email VARCHAR(60) NOT NULL,
    FirstName VARCHAR(20) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    PermissionId INT NOT NULL
);

CREATE TABLE IF NOT EXISTS Employee (
	EmployeeId INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(20) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Salary INT,
    UserId INT,
    CONSTRAINT fk_employee_userid FOREIGN KEY (UserId) REFERENCES Users(UserId)
);

CREATE TABLE IF NOT EXISTS SalesPerson (
	EmployeeId INT PRIMARY KEY,
    FirstName VARCHAR(20),
    LastName VARCHAR(50),
    CONSTRAINT fk_SalesPerson_id FOREIGN KEY (EmployeeId) REFERENCES Employee(EmployeeId)
);

CREATE TABLE IF NOT EXISTS Customer (
	CustomerId INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(20) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    PhoneNumber varchar(10) NOT NULL,
    UserId INT,
    CONSTRAINT fk_customer_userid FOREIGN KEY (UserId) REFERENCES Users(UserId)
);

CREATE TABLE IF NOT EXISTS CustomerAddress (
	AddressId INT AUTO_INCREMENT PRIMARY KEY,
    CustomerId INT,
    Address VARCHAR(40) NOT NULL,
    City VARCHAR(40) NOT NULL,
    State VARCHAR(40) NOT NULL,
    Country VARCHAR(40) NOT NULL,
    Zipcode VARCHAR(5) NOT NULL,
    CONSTRAINT fk_customeraddress_customerid FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId)
);

    
CREATE TABLE IF NOT EXISTS Invoice (
	InvoiceId INT AUTO_INCREMENT PRIMARY KEY,
    InvoiceDate DATE NOT NULL,
    CarId INT NOT NULL,
    CustomerId INT NOT NULL,
    EmployeeId INT NOT NULL,
    CONSTRAINT fk_invoice_carid FOREIGN KEY (CarId) REFERENCES Car(CarId),
    CONSTRAINT fk_invoice_customerid FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId),
    CONSTRAINT fk_invoice_employeeid FOREIGN KEY (EmployeeId) REFERENCES Employee(employeeId)
);