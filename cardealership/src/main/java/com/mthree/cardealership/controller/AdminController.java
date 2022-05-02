package com.mthree.cardealership.controller;

import com.mthree.cardealership.dao.CarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Henry
 */
@Controller
public class AdminController {
    @Autowired
    CarDao carDao;
    
    
    
}
