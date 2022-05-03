package com.mthree.cardealership.controller;

import com.mthree.cardealership.dao.CarDao;
import com.mthree.cardealership.entities.Car;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Henry
 */
@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    CarDao carDao;
    
    @GetMapping("vehicles")
    public String displayCars(Model model){
        List<Car> vehicles = carDao.getAllCars();
        
        model.addAttribute("vehicles", vehicles);
        
        return "vehicles";
    }
    
    @GetMapping("editVehicle")
    public String editVehicle(HttpServletRequest request, Model model){
        int id = Integer.parseInt(request.getParameter("carId"));
        
        Car vehicle = carDao.getCarById(id);
        
        model.addAttribute("vehicle", vehicle);
        
        return "editVehicle";
    }
    
    @PostMapping("editVehicle")
    public String editVehicle(HttpServletRequest request) {
        int carId = Integer.parseInt(request.getParameter("carId"));
        Car vehicle = carDao.getCarById(carId);
        
        vehicle.setMake(request.getParameter("make"));
        vehicle.setModel(request.getParameter("model"));
        vehicle.setTransmission(request.getParameter("transmission"));
        vehicle.setColor(request.getParameter("color"));
        vehicle.setInterior(request.getParameter("interior"));
        vehicle.setVin(request.getParameter("vin"));
        vehicle.setYear(Integer.parseInt(request.getParameter("year")));
        vehicle.setMileage(Integer.parseInt(request.getParameter("mileage")));
        vehicle.setUsed(Boolean.parseBoolean(request.getParameter("used")));
        vehicle.setSold(Boolean.parseBoolean(request.getParameter("sold")));
        vehicle.setSalePrice(new BigDecimal(request.getParameter("salePrice")));
        vehicle.setMsrp(new BigDecimal(request.getParameter("msrp")));
        vehicle.setBodyStyle(request.getParameter("bodyStyle"));
        vehicle.setDescription(request.getParameter("description"));
        vehicle.setImageBinary(null);   //update later
        
        carDao.updateCar(vehicle);
        
        return "redirect:vehicles";
    }
}
