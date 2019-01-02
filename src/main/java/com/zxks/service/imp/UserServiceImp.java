package com.zxks.service.imp;

import com.zxks.common.ServerResponse;
import com.zxks.dao.AdminMapper;
import com.zxks.dao.UserMapper;
import com.zxks.pojo.Admin;
import com.zxks.pojo.User;
import com.zxks.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImp implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private AdminMapper adminMapper;

    @Override
    public ServerResponse<User> userLogin(String idCard, String password) {

        int resultCount = userMapper.checkIdCard(idCard);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }

        User user = userMapper.selectByPrimaryKey(idCard);
        if (user.getPassword().equals(password)) {
            user.setPassword(StringUtils.EMPTY);
            return ServerResponse.createBySuccess("登陆成功", user);
        } else {
            return ServerResponse.createByErrorMessage("密码错误");
        }
    }

    @Override
    public ServerResponse<String> rePassword(String idCard, String oldPassword, String newPassword) {

        User user = userMapper.selectByPrimaryKey(idCard);
        if (user == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        if (oldPassword.equals(user.getPassword())) {
            user.setPassword(newPassword);
            userMapper.updateByPrimaryKeySelective(user);
            return ServerResponse.createBySuccessMessage("修改密码成功");
        } else {
            return ServerResponse.createByErrorMessage("密码错误,修改密码失败");
        }
    }

    @Override
    public ServerResponse<Admin> adminLogin(String username, String password) {

        return null;
    }

    @Override
    public ServerResponse<List<User>> userList() {

        return ServerResponse.createBySuccess(userMapper.selectAllUsers());
    }
}
