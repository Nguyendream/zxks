package com.zxks.service;

import com.zxks.common.ServerResponse;
import com.zxks.pojo.Admin;
import com.zxks.pojo.User;

import java.util.List;

public interface UserService {

    ServerResponse<User> userLogin(String idCard, String password);

    ServerResponse<String> userRegister(User user);

    ServerResponse<String> updateUserInfo(User user);

    ServerResponse<String> rePassword(String idCard, String oldPassword, String newPassword);

    ServerResponse<List<User>> userList();
}
