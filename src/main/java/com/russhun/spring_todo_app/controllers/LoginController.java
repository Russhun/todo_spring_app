package com.russhun.spring_todo_app.controllers;


import com.russhun.spring_todo_app.Constants.UserConstants;
import com.russhun.spring_todo_app.forms.LoginForm;
import com.russhun.spring_todo_app.models.User;
import com.russhun.spring_todo_app.repositories.UserRepository;
import com.russhun.spring_todo_app.services.UserService;
import com.russhun.spring_todo_app.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String login(@ModelAttribute LoginForm loginForm, HttpServletRequest request, Model model) {
        HttpSession httpSession = request.getSession();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        try {
            User user = this.userRepository.findByName(loginForm.getUserName());
            if (user != null)
            {
                if ((user.getName() != null) &&
                        (bCryptPasswordEncoder.matches(loginForm.getPassword(), user.getPassword()))){
                    httpSession.setAttribute(UserConstants.USER_NAME, user.getName());
                    model.addAttribute(new UserConstants());
                    return "redirect:/me";
                }
                return "redirect:/i";
            }
            return "redirect:/i";
        } catch (Exception e){ e.printStackTrace(); return "redirect:/i";}
    }

    @RequestMapping(value = {"/i"}, method = RequestMethod.GET)
    public String loginChecker(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session.getAttribute(UserConstants.USER_NAME) == null)
        {
            model.addAttribute("loginForm", new LoginForm());
            return "i";
        }
        else
            return "redirect:/me";
    }

}
