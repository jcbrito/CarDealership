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
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Henry
 */
@Repository
public class CarDaoDB implements CarDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Car getCarById(int carId) {
        try {
            final String GET_CAR = "SELECT * FROM car WHERE CarId = ?";
            return jdbc.queryForObject(GET_CAR, new CarMapper(), carId);
        } catch (DataAccessException e) {
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
        try {
            year = Integer.valueOf(searchTerm);
        } catch (NumberFormatException e) {
            year = 0;
        }

        Stack<String> filters = new Stack<>();

        if (minPrice != 0) {
            filters.push("car.salePrice >= " + minPrice);
        }
        if (maxPrice != 0) {
            filters.push("car.salePrice <= " + minPrice);
        }
        if (minYear != 0) {
            filters.push("car.year >= " + minYear);
        }
        if (maxYear != 0) {
            filters.push("car.year <= " + maxYear);
        }
        if (trimmedTerm.length() > 0) {
            filters.push("(car.make LIKE '%" + trimmedTerm + "%'"
                    + "OR car.model LIKE '%" + trimmedTerm + "%')");
        }
        if (year != 0) {
            filters.push("car.year = " + year);
        }

        if (!filters.isEmpty()) {
            GET_CARS_WITH_FILTERS += " WHERE ";

            while (true) {
                GET_CARS_WITH_FILTERS += filters.pop();
                if (!filters.isEmpty()) {
                    GET_CARS_WITH_FILTERS += " AND ";
                } else {
                    break;
                }
            }
        }

        return jdbc.query(GET_CARS_WITH_FILTERS, new CarMapper());
    }

    @Override
    @Transactional
    public Car addCar(Car car) {
        final String ADD_CAR = "INSERT INTO car(Vin, Make, Model, CarDescription, "
                + "CarYear, SalePrice, MSRP, Color, Interior, BodyStyle, Transmission, "
                + "Mileage, Used, Sold, ImageBinary) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbc.update(ADD_CAR,
                car.getVin(),
                car.getMake(),
                car.getModel(),
                car.getDescription(),
                car.getYear(),
                car.getSalePrice(),
                car.getMsrp(),
                car.getColor(),
                car.getInterior(),
                car.getBodyStyle(),
                car.getTransmission(),
                car.getMileage(),
                car.isUsed(),
                car.isSold(),
                car.getImageBinary()
        );

        car.setCarId(jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class));

        return car;
    }

    @Override
    public void updateCar(Car car) {
        final String UPDATE_CAR = "UPDATE car SET Vin = ?, Make = ?, Model = ?, CarDescription = ?, "
                + "CarYear = ?, SalePrice = ?, MSRP = ?, Color = ?, Interior = ?, BodyStyle = ?, Transmission = ?, "
                + "Mileage = ?, Used = ?, Sold = ?, ImageBinary = ? WHERE CarId = ?";

        jdbc.update(UPDATE_CAR,
                car.getVin(),
                car.getMake(),
                car.getModel(),
                car.getDescription(),
                car.getYear(),
                car.getSalePrice(),
                car.getMsrp(),
                car.getColor(),
                car.getInterior(),
                car.getBodyStyle(),
                car.getTransmission(),
                car.getMileage(),
                car.isUsed(),
                car.isSold(),
                car.getImageBinary(),
                car.getCarId()
        );

    }

    @Override
    @Transactional
    public void deleteCarById(int carId) {

        //De;ete sales invoices that reference the car to be deleted
        final String DELETE_INVOICE = "DELETE FROM invoice WHERE invoice.CarId = ?";
        jdbc.update(DELETE_INVOICE, carId);

        final String DELETE_CAR = "DELETE FROM car WHERE CarId = ?";
        jdbc.update(DELETE_CAR, carId);

    }

    @Override
    public List<Car> getAllSpecials() {

        final String GET_CARS = "select * from car "
                + "join special on car.carid = special.carid";
        return jdbc.query(GET_CARS, new CarMapper());

    }

    public static final class CarMapper implements RowMapper<Car> {

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
            car.setDescription(rs.getString("CarDescription"));
            car.setImageBinary(rs.getBlob("ImageBinary"));

            return car;
        }
    }
}
