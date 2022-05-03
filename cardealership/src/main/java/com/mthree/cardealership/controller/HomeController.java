/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mthree.cardealership.controller;

import com.mthree.cardealership.dao.CarDao;
import com.mthree.cardealership.entities.Car;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Juan
 */
@Controller
public class HomeController {

    @Autowired
    CarDao dao;

    @RequestMapping("home")
    public String mainPage(Model model) {

        List<Car> cars = dao.getAllCars();
        model.addAttribute("cars", cars);

        return "home";
    }


    @GetMapping("new")
    public String displayNewCars( Model model, String makeModelYear, String priceMin, String priceMax, String yearMax, String yearMin) {
        //getting all fields from the search bar
        //year make or model

        
        List<Car> cars = dao.getAllCars();

        cars = cars.stream().filter((c) -> !c.isUsed()).collect(Collectors.toList());

        if (makeModelYear != null && !makeModelYear.equalsIgnoreCase("")) {
            cars = cars.stream().filter((c) -> c.getMake().equalsIgnoreCase(makeModelYear)
                    || c.getModel().equalsIgnoreCase(makeModelYear)
                    || (c.getYear() + "").equalsIgnoreCase(makeModelYear))
                    .collect(Collectors.toList());
        }

        // if min price is not empty look for it
        if (priceMin != null && !priceMin.equalsIgnoreCase("none")) {
            cars = cars.stream().filter((c) -> c.getSalePrice().compareTo(new BigDecimal(priceMin)) >= 0)
                    .collect(Collectors.toList());
        }

        //if max price is provided filter the lost again
        if (priceMax != null && !priceMax.equalsIgnoreCase("none")) {
            cars = cars.stream().filter((c) -> c.getSalePrice().compareTo(new BigDecimal(priceMax)) <= 0)
                    .collect(Collectors.toList());
        }

        // the same for max year
        if (yearMax != null && !yearMax.equalsIgnoreCase("none")) {
            cars = cars.stream().filter((c) -> c.getYear() <= Integer.parseInt(yearMax))
                    .collect(Collectors.toList());
        }

        // checking min year
        if (yearMin != null && !yearMin.equalsIgnoreCase("none")) {
            cars = cars.stream().filter((c) -> c.getYear() >= Integer.parseInt(yearMin))
                    .collect(Collectors.toList());
        }

        model.addAttribute("cars", cars);

        return "new";
    }
    
    
    @GetMapping("used")
    public String displayUsedCars(Model model, String makeModelYear, String priceMin, String priceMax, String yearMax, String yearMin) {

        //getting all fields from the search bar
        //year make or model

        
        List<Car> cars = dao.getAllCars();

        cars = cars.stream().filter((c) -> c.isUsed()).collect(Collectors.toList());

        if (makeModelYear != null && !makeModelYear.equalsIgnoreCase("")) {
            cars = cars.stream().filter((c) -> c.getMake().equalsIgnoreCase(makeModelYear)
                    || c.getModel().equalsIgnoreCase(makeModelYear)
                    || (c.getYear() + "").equalsIgnoreCase(makeModelYear))
                    .collect(Collectors.toList());
        }

        // if min price is not empty look for it
        if (priceMin != null && !priceMin.equalsIgnoreCase("none")) {
            cars = cars.stream().filter((c) -> c.getSalePrice().compareTo(new BigDecimal(priceMin)) >= 0)
                    .collect(Collectors.toList());
        }

        //if max price is provided filter the lost again
        if (priceMax != null && !priceMax.equalsIgnoreCase("none")) {
            cars = cars.stream().filter((c) -> c.getSalePrice().compareTo(new BigDecimal(priceMax)) <= 0)
                    .collect(Collectors.toList());
        }

        // the same for max year
        if (yearMax != null && !yearMax.equalsIgnoreCase("none")) {
            cars = cars.stream().filter((c) -> c.getYear() <= Integer.parseInt(yearMax))
                    .collect(Collectors.toList());
        }

        // checking min year
        if (yearMin != null && !yearMin.equalsIgnoreCase("none")) {
            cars = cars.stream().filter((c) -> c.getYear() >= Integer.parseInt(yearMin))
                    .collect(Collectors.toList());
        }

        model.addAttribute("cars", cars);

        return "used";

    }

    @GetMapping("homeSpecials")
    public String displaySpecials(Model model) {

        List<Car> cars = dao.getAllCars();
        model.addAttribute("cars", cars);

        return "homeSpecials";
    }

    @GetMapping("contact")
    public String displayContact(Model model) {

        return "contact";
    }

}
