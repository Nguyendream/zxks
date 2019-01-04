package com.zxks.controller;

import com.zxks.common.Const;
import com.zxks.common.ServerResponse;
import com.zxks.pojo.Admin;
import com.zxks.service.AdminService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/admin/")
public class AdminController {

    @Resource
    private AdminService adminService;

    private ServerResponse<String> checkAdmin(HttpSession session) {

        Admin admin = (Admin) session.getAttribute(Const.CURRENT_ADMIN);
        if (admin == null) {
            return ServerResponse.createByErrorMessage("管理员未登陆");
        }
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ServerResponse<Admin> login(String username, String password) {

        return null;
    }
}
