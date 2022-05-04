package com.mthree.cardealership.controller;

import ch.qos.logback.core.status.Status;
import com.mthree.cardealership.dao.UserDao;
import com.mthree.cardealership.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


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

    @GetMapping("login")
    public String login(Model model) {
        List<User> users = userDao.getAllUsers();
        model.addAttribute("users", users);
        return "/login";
    }

    @PostMapping("login")
    public String loginUser(HttpServletRequest request, Model model, HttpSession session) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = userDao.getUserByEmail(email);
        model.addAttribute("users", user);

        if (user != null && userDao.checkPassword(user, password)) {
            session.setAttribute("email", email);
            return "redirect:/profile?success=true";
        }

        return "redirect:/login?error=true";
    }

    @GetMapping("profile")
    public String profile() {
//        List<User> users = userDao.getAllUsers();
//        model.addAttribute("users", users);
        return "profile";
    }

    @GetMapping("userSession")
    public String profileSession(Model model, HttpSession session) {
        List<User> users = userDao.getAllUsers();
        model.addAttribute("users", users);

        return "profile";
    }

    @RequestMapping("logout")
    public String logOut(HttpSession session) {
        session.removeAttribute("email");

        return "redirect:/login?success=true";
    }

    @GetMapping("addUser")
    public String addUser(Model model) {
        return "addUser";
    }

    @PostMapping("addUser")
    public String performAddUser(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("passwordConfirm");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        User user = new User();
        user.setEmail(email);
        if (password.matches(confirmPassword)) {
            user.setPassword(userDao.hashPassword(password));
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);

        userDao.addUser(user);

        return "redirect:/login";
    }

}
