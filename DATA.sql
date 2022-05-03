USE CarDealerShip;

INSERT INTO Car(Vin, Make, Model, CarDescription, CarYear, SalePrice, MSRP, Color, Interior, BodyStyle, Transmission, Mileage, Used, Sold, ImageBinary) 
VALUES
("111", "Honda", "CRX", "Cursed Yellow Car", 1991, 5500, 4000, "Yellow", "Black", "Coupe", "Automatic", 121000, True, False, null),
("222", "Mercedes-Benz", "CLK GTR", "Sporty and supernatural", 1995, 21000, 24000, "Silver", "Black", "Sports Car", "Automatic", 55000, True, False, null),
("333", "Toyota", "AE86 Hakone Edition", "Iconic tofu delivery car", 1986, 31800, 10000, "Black/White", "Black", "Coupe", "Manual", 80000, True, False, null);

INSERT INTO Users VALUES(1, "henry@gmail.com", "1234", "Henry", "Perrottet", 1, False);
INSERT INTO Users VALUES(2, "lewi@gmail.com", "1234", "Lewi", "Adrong", 1, False);

INSERT INTO special VALUES(1, 1), (2, 2), (3, 3);

SELECT * FROM Car;

