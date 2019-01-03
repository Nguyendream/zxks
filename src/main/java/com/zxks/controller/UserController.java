package com.zxks.controller;

import com.sun.org.apache.regexp.internal.RE;
import com.zxks.common.Const;
import com.zxks.common.ServerResponse;
import com.zxks.pojo.User;
import com.zxks.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/user/")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public ServerResponse<User> login(String idCard, String password, HttpSession session) {

        ServerResponse<User> response = userService.userLogin(idCard, password);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
            session.setMaxInactiveInterval(3600*2);//session超时时间(秒)
        }

        return response;
    }

    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    public ServerResponse<String> logout(HttpSession session) {

        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user != null) {
            session.removeAttribute(Const.CURRENT_USER);
            session.removeAttribute(Const.CURRENT_EXAM);
            return ServerResponse.createBySuccessMessage("注销成功");
        }
        return ServerResponse.createByErrorMessage("注销失败，未登陆");
    }

    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    public ServerResponse<String> register(User user) {

        return userService.userRegister(user);
    }

    @RequestMapping(value = "update_user_info.do", method = RequestMethod.POST)
    public ServerResponse<String> updateUserInfo(User newUserInfo, HttpSession session) {

        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }

        newUserInfo.setIdCard(user.getIdCard());
        ServerResponse<String> response =  userService.updateUserInfo(newUserInfo);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, newUserInfo);
            return response;
        }
        return  response;

    }

    @RequestMapping(value = "update_password.do", method = RequestMethod.POST)
    public ServerResponse<String> rePassword(String idCard, String oldPassword, String newPassword) {

        return userService.rePassword(idCard,oldPassword,newPassword);
    }

    @RequestMapping(value = "get_user_info.do", method = RequestMethod.POST)
    public ServerResponse<User> getUserInfo(HttpSession session) {

        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user != null) {
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登陆，无法获取信息");
    }

    /*@RequestMapping(value = "list_users.do", method = RequestMethod.POST)
    public ServerResponse<List<User>> listUsers() {

        return userService.userList();
    }*/
}
