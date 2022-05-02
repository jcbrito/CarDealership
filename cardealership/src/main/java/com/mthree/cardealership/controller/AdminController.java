package com.mthree.cardealership.controller;

import com.mthree.cardealership.dao.CarDao;
import com.mthree.cardealership.entities.Car;
import java.util.List;
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
        List<Car> cars = carDao.getAllCars();
        
        
        
        return "vehicles";
    }
    
    
}
