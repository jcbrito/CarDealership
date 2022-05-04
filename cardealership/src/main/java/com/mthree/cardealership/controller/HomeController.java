/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mthree.cardealership.controller;

import com.mthree.cardealership.dao.CarDao;
import com.mthree.cardealership.entities.Car;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Juan
 */
@Controller
//@RequestMapping("home")
public class HomeController {

    @Autowired
    CarDao dao;

    @RequestMapping("home")
    public String mainPage(Model model) {

        List<Car> cars = dao.getAllCars();
        List<Car> specials = dao.getAllSpecials();
        model.addAttribute("cars", cars);
        model.addAttribute("specials", specials);
        
//        List<ResponseEntity<byte[]>> images= new ArrayList<>();
//        
//        specials.stream().forEach((s)-> images.add(getImage(s.getCarId())));
//       
//              
//        model.addAttribute("images", images);

        return "home";
    }

     /**
     * Retrieves the image BLOB for a given car ID and builds an HTTP response
     * entity that can be used in the src attribute of an image tag
     * @param carId
     * @return ResponseEntity
     */
    @GetMapping("image")
    public ResponseEntity<byte[]> getImage(@RequestParam int carId){
        
        try{
            Blob imgBlob = dao.getCarById(carId).getImageBinary();
            byte[] imgBinary = imgBlob.getBytes(1, (int)imgBlob.length());
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            
            return new ResponseEntity<>(
                    imgBinary, 
                    headers,
                    HttpStatus.OK
            );
            
        }
        catch(SQLException e){
            return null;
        }
        
    }
    
    
    
    @GetMapping("new")
    public String displayNewCars(Model model, String makeModelYear, String priceMin, String priceMax, String yearMax, String yearMin) {
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

        List<Car> cars = dao.getAllSpecials();
        model.addAttribute("cars", cars);

        return "homeSpecials";
    }

    @GetMapping("carDetail")
    public String courseDetail(Integer id, Model model) {
        Car car = dao.getCarById(id);
        model.addAttribute("car", car);
        return "details";
    }

    @GetMapping("contact")
    public String displayContact(Model model) {

        
        return "contact";
    }

}
