package com.zxks.controller;

import com.zxks.common.Const;
import com.zxks.pojo.Admin;
import com.zxks.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ViewController {

    @GetMapping("/")
    public String index(HttpSession session) {

        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return "redirect:/login";
        }
        return "exam_paper";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/user_info")
    public String user_info(HttpSession session) {

        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return "redirect:/login";
        }
        return "user_info";
    }

    @GetMapping("/re_password")
    public String re_password(HttpSession session) {

        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return "redirect:/login";
        }
        return "re_password";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

}
