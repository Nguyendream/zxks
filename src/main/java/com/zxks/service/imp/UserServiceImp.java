package com.zxks.service.imp;

import com.zxks.common.ServerResponse;
import com.zxks.dao.AdminMapper;
import com.zxks.dao.UserMapper;
import com.zxks.pojo.Admin;
import com.zxks.pojo.User;
import com.zxks.service.UserService;
import com.zxks.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImp implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> userLogin(String idCard, String password) {

        int resultCount = userMapper.checkIdCard(idCard);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }

        String md5Password = MD5Util.MD5EncodeUtf8(password);

        User user = userMapper.selectByPrimaryKey(idCard);
        if (user.getPassword().equals(md5Password)) {
            user.setPassword(StringUtils.EMPTY);
            return ServerResponse.createBySuccess("登陆成功", user);
        } else {
            return ServerResponse.createByErrorMessage("密码错误");
        }
    }

    @Override
    public ServerResponse<String> userRegister(User user) {

        if (user == null) {
            return ServerResponse.createByErrorMessage("参数为空");
        }

        int resultCount = userMapper.checkIdCard(user.getIdCard());
        if (resultCount != 0) {
            return ServerResponse.createByErrorMessage("已注册的准考证号");
        }

        String md5Password = MD5Util.MD5EncodeUtf8(user.getPassword());
        user.setPassword(md5Password);

        resultCount = userMapper.insert(user);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    @Override
    public ServerResponse<String> updateUserInfo(User user) {

        if (user == null) {
            return ServerResponse.createByErrorMessage("参数为空");
        }

        int resultCount = userMapper.checkIdCard(user.getIdCard());
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }

        resultCount = userMapper.updateByPrimaryKeySelective(user);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("修改信息失败");
        }
        return ServerResponse.createBySuccessMessage("修改信息成功");
    }

    @Override
    public ServerResponse<String> rePassword(String idCard, String oldPassword, String newPassword) {

        User user = userMapper.selectByPrimaryKey(idCard);
        if (user == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }

        String md5OldPassword = MD5Util.MD5EncodeUtf8(oldPassword);
        String md5NewPassword = MD5Util.MD5EncodeUtf8(newPassword);

        if (md5OldPassword.equals(user.getPassword())) {
            user.setPassword(md5NewPassword);
            userMapper.updateByPrimaryKeySelective(user);
            return ServerResponse.createBySuccessMessage("修改密码成功");
        } else {
            return ServerResponse.createByErrorMessage("密码错误,修改密码失败");
        }
    }

    @Override
    public ServerResponse<List<User>> userList() {

        return ServerResponse.createBySuccess(userMapper.selectAllUsers());
    }
}
