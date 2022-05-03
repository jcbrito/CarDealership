package com.mthree.cardealership.controller;

import com.mthree.cardealership.dao.CarDao;
import com.mthree.cardealership.dao.UserDao;
import com.mthree.cardealership.entities.Car;
import com.mthree.cardealership.entities.User;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Henry
 */
@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    CarDao carDao;
    
    @Autowired
    UserDao userDao;
    
    @GetMapping("vehicles")
    public String displayCars(Model model){
        List<Car> vehicles = carDao.getAllCars();
        
        model.addAttribute("vehicles", vehicles);
        
        return "vehicles";
    }
    
    @GetMapping("vehicles/q")
    public String searchCars(Model model, @RequestParam String search,
            @RequestParam int yearMin, @RequestParam int yearMax,
            @RequestParam int priceMin, @RequestParam int priceMax){
        
        List<Car> results = carDao.searchCars(search, priceMin, priceMax, yearMin, yearMax);
        
        model.addAttribute("vehicles", results);
        
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
    public String editVehicle(HttpServletRequest request) throws Exception {
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
        
        //read passed img Part to byte array to blob for storage
        InputStream in = request.getPart("picture").getInputStream();
        byte[] imgBinary = new byte[in.available()];
        in.read(imgBinary);
        Blob blob = new SerialBlob( imgBinary );
        vehicle.setImageBinary( blob );  
        //
        
        carDao.updateCar(vehicle);
        
        return "redirect:vehicles";
    }
    
    @GetMapping("addVehicle")
    public String addVehicle(){
        return "addVehicle";
    }
    
    @PostMapping("addVehicle")
    public String performAddVehicle(HttpServletRequest request) throws Exception{
        Car vehicle = new Car();
        
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
        
        //read passed img Part to byte array to blob for storage
        InputStream in = request.getPart("picture").getInputStream();
        byte[] imgBinary = new byte[in.available()];
        in.read(imgBinary);
        Blob blob = new SerialBlob( imgBinary );
        vehicle.setImageBinary( blob );  
        //
        
        carDao.addCar(vehicle);
        
        return "redirect:vehicles";
    }
    
    /**
     * Retrieves the image BLOB for a given car ID and builds an HTTP response
     * entity that can be used in the src attribute of an image tag
     * @param carId
     * @return ResponseEntity
     */
    @GetMapping("getImage")
    public ResponseEntity<byte[]> getImage(@RequestParam int carId){
        
        try{
            Blob imgBlob = carDao.getCarById(carId).getImageBinary();
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
    
    @GetMapping("users")
    public String displayUsers(Model model){
        List<User> users = userDao.getAllUsers();
        
        model.addAttribute("users", users);
        
        return "users";
    }
}
