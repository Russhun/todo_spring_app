package com.russhun.spring_todo_app.controllers;

import com.russhun.spring_todo_app.Constants.UserConstants;
import com.russhun.spring_todo_app.forms.RegForm;
import com.russhun.spring_todo_app.models.Memo;
import com.russhun.spring_todo_app.models.User;
import com.russhun.spring_todo_app.repositories.MemoRepository;
import com.russhun.spring_todo_app.repositories.UserRepository;
import com.russhun.spring_todo_app.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RegController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = {"/reg"}, method = RequestMethod.POST)
    public String reg(@ModelAttribute RegForm regForm, HttpServletRequest httpRequest, HttpServletResponse response, Model model) {
        HttpSession httpSession = httpRequest.getSession();
        User user = userRepository.findByName(regForm.getUserName());
        if (user == null) {
            User newUser = new User(regForm.getUserName(), 1,
                    PasswordEncoder.encodePassword(regForm.getPassword()));
            userRepository.save(newUser);
            httpSession.setAttribute(UserConstants.USER_NAME, newUser.getName());
            model.addAttribute(new UserConstants());
            return "redirect:/me";
        }
        return "redirect:/r";
    }

    @RequestMapping(value = {"/r"}, method = RequestMethod.GET)
    public String regChecker(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session.getAttribute(UserConstants.USER_NAME) == null) {
            model.addAttribute(new RegForm());
            return "r";
        }
        else
            return "redirect:/me";
    }

}
