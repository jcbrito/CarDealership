/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mthree.cardealership.controller;

import com.mthree.cardealership.dao.CarDao;
import com.mthree.cardealership.entities.Car;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String index(Model model) {

        List<Car> cars = dao.getAllCars();
        model.addAttribute("/", cars);

        return "/";
    }

    @GetMapping("new")
    public String displayTeachers(Model model) {

        return "new";
    }

}
