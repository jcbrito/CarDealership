package com.mthree.cardealership.dao;

import com.mthree.cardealership.entities.Car;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Henry
 */
@Repository
public class CarDaoDB implements CarDao{
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Car getCarById(int carId) {
        try {
            final String GET_CAR = "SELECT * FROM car WHERE CarId = ?";
            return jdbc.queryForObject(GET_CAR, new CarMapper(), carId);
        }
        catch(DataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Car> getAllCars() {
        final String GET_CARS = "SELECT * FROM car";
        return jdbc.query(GET_CARS, new CarMapper());
    }

    @Override
    public List<Car> searchCars(String searchTerm, int minPrice, int maxPrice, int minYear, int maxYear) {
        String GET_CARS_WITH_FILTERS = "SELECT * FROM car";
        
        String trimmedTerm = searchTerm.trim();
        int year;
        try{
            year = Integer.valueOf(searchTerm);
        }
        catch(NumberFormatException e){
            year = 0;
        }
        
        Stack<String> filters = new Stack<>();
        
        if(minPrice != 0) filters.push("car.salePrice >= " + minPrice);
        if(maxPrice != 0) filters.push("car.salePrice <= " + minPrice);
        if(minYear != 0) filters.push("car.year >= " + minYear);
        if(maxYear != 0) filters.push("car.year <= " + maxYear);
        if(trimmedTerm.length() > 0) filters.push("(car.make LIKE '%"+trimmedTerm+"%'"
                + "OR car.model LIKE '%"+trimmedTerm+"%')");
        if(year != 0) filters.push("car.year = " + year);
        
        if(!filters.isEmpty()){
            GET_CARS_WITH_FILTERS += " WHERE ";
            
            while(true){
                GET_CARS_WITH_FILTERS += filters.pop();
                if(!filters.isEmpty()){
                    GET_CARS_WITH_FILTERS += " AND ";
                }
                else{
                    break;
                }
            }
        }
        
        return jdbc.query(GET_CARS_WITH_FILTERS, new CarMapper());
    }

    @Override
    public Car addCar(Car car) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Car updateCar(Car car) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Car deleteCarById(int carId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    public static final class CarMapper implements RowMapper<Car>{
        @Override
        public Car mapRow(ResultSet rs, int index) throws SQLException {
            Car car = new Car();
            car.setCarId(rs.getInt("CarId"));
            car.setMake(rs.getString("Make"));
            car.setModel(rs.getString("Model"));
            car.setTransmission(rs.getString("Transmission"));
            car.setColor(rs.getString("Color"));
            car.setInterior(rs.getString("Interior"));
            car.setVin(rs.getString("Vin"));
            car.setYear(rs.getInt("CarYear"));
            car.setMileage(rs.getInt("Mileage"));
            car.setUsed(rs.getBoolean("Used"));
            car.setSold(rs.getBoolean("Sold"));
            car.setSalePrice(rs.getBigDecimal("SalePrice"));
            car.setMsrp(rs.getBigDecimal("MSRP"));
            car.setBodyStyle(rs.getString("BodyStyle"));
            
            return car;
        }
    }
}
