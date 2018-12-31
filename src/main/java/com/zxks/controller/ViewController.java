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
        if (user != null) {
            return "index";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

}
