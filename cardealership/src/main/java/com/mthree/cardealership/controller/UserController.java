package com.mthree.cardealership.controller;

import ch.qos.logback.core.status.Status;
import com.mthree.cardealership.dao.UserDao;
import com.mthree.cardealership.entities.User;
import com.mthree.cardealership.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value="/addUser", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("addUser");
        return modelAndView;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
//        User userExists = userService.findUserByEmail(user.getEmail());
        if (userDao.getUserByEmail(user.getEmail()) != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already an email registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("addUser");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("addUser");

        }
        return modelAndView;
    }

//    @GetMapping("/login")
//    public String login(Model model) {
//        List<User> users = userDao.getAllUsers();
//        model.addAttribute("users", users);
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public String loginUser(HttpServletRequest request, Model model, HttpSession session) {
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        User user = userDao.getUserByEmail(email);
//        model.addAttribute("users", user);
//
//        if (user != null && userDao.checkPassword(user, password)) {
//            session.setAttribute("email", email);
//            return "redirect:/profile?success=true";
//        }
//
//        return "redirect:/login?error=true";
//    }

    @GetMapping("/profile")
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

//    @GetMapping("addUser")
//    public String addUser(Model model) {
//        return "addUser";
//    }
//
//    @PostMapping("addUser")
//    public String performAddUser(HttpServletRequest request) {
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        String confirmPassword = request.getParameter("passwordConfirm");
//        String firstName = request.getParameter("firstName");
//        String lastName = request.getParameter("lastName");
//
//        User user = new User();
//        user.setEmail(email);
//        if (password.matches(confirmPassword)) {
//            user.setPassword(userDao.hashPassword(password));
//        }
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//
//        userDao.addUser(user);
//
//        return "redirect:/login";
//    }

}
