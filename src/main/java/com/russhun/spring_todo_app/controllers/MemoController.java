package com.russhun.spring_todo_app.controllers;


import com.russhun.spring_todo_app.Constants.UserConstants;
import com.russhun.spring_todo_app.forms.MemoForm;
import com.russhun.spring_todo_app.models.Memo;
import com.russhun.spring_todo_app.models.User;
import com.russhun.spring_todo_app.repositories.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MemoController {

    @Autowired
    MemoRepository memoRepository;


    @RequestMapping(value = {"/memo/add"}, method = RequestMethod.POST)
    public String addMemo(@ModelAttribute MemoForm memoForm, HttpServletRequest request) {


        // TODO auth check
        HttpSession session = request.getSession();
        String userName = (String)session.getAttribute(UserConstants.USER_NAME);
        Memo memo = new Memo(memoForm.getTitle(), memoForm.getText(), new User(userName, 1, ""));
        memoRepository.save(memo);
        return "redirect:/me";
    }

    @RequestMapping(value = {"/memo/del"}, method = RequestMethod.POST)
    public String deleteMemo(@RequestParam(name = "memoId") Integer memoId, HttpServletRequest request) {

        // TODO auth check
        HttpSession session = request.getSession();
        if (memoId == 0){
            System.out.println("С таким id нету мемо");
        }
        else {
            try {
                Memo memo = memoRepository.findById(memoId.intValue());
                if (memo != null)
                    memoRepository.delete(memo);
            } catch (NumberFormatException e) {
                System.out.println(String.format("Что-то не так с memoId %s", memoId));
            }
        }

        return "redirect:/me";
    }
}
