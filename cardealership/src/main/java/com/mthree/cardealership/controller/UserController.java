package com.mthree.cardealership.controller;

import ch.qos.logback.core.status.Status;
import com.mthree.cardealership.dao.UserDao;
import com.mthree.cardealership.entities.Car;
import com.mthree.cardealership.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("login")
    public String login(Model model) {
        List<User> users = userDao.getAllUsers();
        model.addAttribute("users", users);
        return "login";
    }

    @PostMapping("login")
    public String loginUser(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = userDao.getUserByEmail(email);
        model.addAttribute("users", user);

        if (user != null && password.equals(user.getPassword())) {
            user.setLoggedIn(true);
            return "redirect:/profile";
        }
        return "login";
    }

    @GetMapping("profile")
    public String profile(Model model) {
        List<User> users = userDao.getAllUsers();
        model.addAttribute("users", users);
        return "profile";
    }
}
