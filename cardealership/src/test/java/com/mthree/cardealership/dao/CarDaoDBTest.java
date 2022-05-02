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

/**
 *
 * @author Henry
 */
public class CarDaoDBTest {
    
    @Autowired
    CarDao carDao;
    
    public CarDaoDBTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<Car> cars = carDao.getAllCars();
        
        for(Car c : cars) {
            carDao.deleteCarById(c.getCarId());
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getCarById method, of class CarDaoDB.
     */
    @Test
    public void testGetCarById() {
    }

    /**
     * Test of getAllCars method, of class CarDaoDB.
     */
    @Test
    public void testGetAllCars() {
        Car car1 = new Car();
        car1.setMake("TestMake");
        car1.setModel("TestModel");
        car1.setColor("Testcolor");
        car1.setInterior("TestInterior");
        car1.setTransmission("TestTrans");
        car1.setBodyStyle("TestBodyStyle");
        car1.setDescription("descriptionHere");
        car1.setYear(2001);
        car1.setSalePrice(new BigDecimal("1400.00") );
        car1.setMsrp(new BigDecimal("1500.00") );
        car1.setMileage(150000);
        car1.setVin("testvin");
        car1.setUsed(true);
        car1.setSold(false);
        car1.setImageBinary(null);
        
        car1 = carDao.addCar(car1);
        
        Car car2 = new Car();
        car2.setMake("TestMake2");
        car2.setModel("TestModel2");
        car2.setColor("Testcolor2");
        car2.setInterior("TestInterior2");
        car2.setTransmission("TestTrans2");
        car2.setBodyStyle("TestBodyStyle2");
        car2.setDescription("descriptionHere2");
        car2.setYear(2002);
        car2.setSalePrice(new BigDecimal("1402.00") );
        car2.setMsrp(new BigDecimal("1502.00") );
        car2.setMileage(122222);
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

    /**
     * Test of searchCars method, of class CarDaoDB.
     */
    @Test
    public void testSearchCars() {
    }

    /**
     * Test of addCar method, of class CarDaoDB.
     */
    @Test
    public void testAddCar() {
    }

    /**
     * Test of updateCar method, of class CarDaoDB.
     */
    @Test
    public void testUpdateCar() {
    }

    /**
     * Test of deleteCarById method, of class CarDaoDB.
     */
    @Test
    public void testDeleteCarById() {
    }
    
}
