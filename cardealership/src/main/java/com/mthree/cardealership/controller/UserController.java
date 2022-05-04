package com.mthree.cardealership.controller;

import ch.qos.logback.core.status.Status;
import com.mthree.cardealership.dao.UserDao;
import com.mthree.cardealership.entities.User;
import com.mthree.cardealership.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 *
 * @author Lewi
 */

@Controller
public class UserController {

    @Autowired
    UserDao userDao;

    @Autowired
    UserService userService;

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    @RequestMapping("/login")
    public String login(){
        if (isAuthenticated()) {
            return "profile";
        }
        return "login";
    }

    @GetMapping("/addUser")
    public String registration(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "addUser";
    }

    @RequestMapping("/addUser")
    public String createNewUser(@Valid User user, Model model, BindingResult bindingResult) {
        if (userDao.getUserByEmail(user.getEmail()) != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already an email registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            return "addUser";
        } else {
            userService.saveUser(user);
            model.addAttribute("successMessage", "User has been registered successfully");
            model.addAttribute("user", new User());
            return "login";

        }
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @GetMapping("/editUser")
    public String editUser() {
        return "editUser";
    }

    @PostMapping("/editUser")
    public String performEditUser() {
        return "editUser";
    }

    @GetMapping("/addVehicle")
    public String addVehicle() {
        return "addVehicle";
    }

    @GetMapping("/editVehicle")
    public String editVehicle() {
        return "editVehicle";
    }

    @PostMapping("/editVehicle")
    public String performEditVehicle() {
        return "editVehicle";
    }

}
