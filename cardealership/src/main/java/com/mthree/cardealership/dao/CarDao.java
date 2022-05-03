/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.Car;
import java.util.List;

/**
 *
 * @author Juan
 */
public interface CarDao {
    Car getCarById(int carId);
    List<Car> getAllCars();
    List<Car> searchCars(String searchTerm, int minPrice, int maxPrice, 
            int minYear, int maxYear);
    Car addCar(Car car);
    void updateCar(Car car);
    void deleteCarById(int carId);
    List<Car> getAllSpecials();
}
