package com.russhun.spring_todo_app.controllers;

import com.russhun.spring_todo_app.Constants.UserConstants;
import com.russhun.spring_todo_app.forms.MemoForm;
import com.russhun.spring_todo_app.models.Memo;
import com.russhun.spring_todo_app.models.User;
import com.russhun.spring_todo_app.repositories.MemoRepository;
import com.russhun.spring_todo_app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class UserAccountController {

    @Autowired
    MemoRepository memoRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = {"/me"})
    public String me(HttpServletRequest request, Model model) {
        model.addAttribute(new UserConstants());
        HttpSession session = request.getSession();
        try {
            User user = userRepository.findByName((String)session.getAttribute(UserConstants.USER_NAME));
            ArrayList<Memo> memos = memoRepository.findAllByUser(user);
            if (memos != null) {
                model.addAttribute("memos", memos);
            }
            else
                model.addAttribute("memos", new ArrayList<Memo>());
        } catch (Exception e){e.printStackTrace();}
        model.addAttribute("memoForm", new MemoForm());
        return "me";
    }
}
