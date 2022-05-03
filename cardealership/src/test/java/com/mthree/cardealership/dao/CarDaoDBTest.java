/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.Car;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Henry
 */
@SpringBootTest
public class CarDaoDBTest {
    
    @Autowired
    CarDao carDao;
    
 
    @BeforeEach
    public void setUp() {
        List<Car> cars = carDao.getAllCars();
        
        for(Car c : cars) {
            carDao.deleteCarById(c.getCarId());
        }
    }
    


    /**
     * Test of getAllCars method, of class CarDaoDB.
     */
    @Test
    public void testGetAllCars() {
        Car car1 = new Car();
        car1.setMake("Ford");
        car1.setModel("Festiva");
        car1.setColor("Red");
        car1.setInterior("Gray");
        car1.setTransmission("Manual");
        car1.setBodyStyle("Coupe");
        car1.setDescription("A silly little red car");
        car1.setYear(1990);
        car1.setSalePrice(new BigDecimal("775.00") );
        car1.setMsrp(new BigDecimal("7750.00") );
        car1.setMileage(234567);
        car1.setVin("testvin");
        car1.setUsed(true);
        car1.setSold(false);
        car1.setImageBinary(null);
        
        car1 = carDao.addCar(car1);
        
        Car car2 = new Car();
        car2.setMake("Honda");
        car2.setModel("CRX");
        car2.setColor("Yellow");
        car2.setInterior("Black");
        car2.setTransmission("Manual");
        car2.setBodyStyle("Coupe");
        car2.setDescription("This car is cursed");
        car2.setYear(1991);
        car2.setSalePrice(new BigDecimal("2999.00") );
        car2.setMsrp(new BigDecimal("6729.00") );
        car2.setMileage(155000);
        car2.setVin("testvin2");
        car2.setUsed(true);
        car2.setSold(false);
        car2.setImageBinary(null);
        
        car2 = carDao.addCar(car2);
        
        List<Car> cars = carDao.getAllCars();
        
        assertEquals(2, cars.size());
        assertTrue( cars.contains(car1) );
        assertTrue( cars.contains(car2) );
    }

}
