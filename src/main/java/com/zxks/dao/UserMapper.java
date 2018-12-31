package com.zxks.dao;

import com.zxks.pojo.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String idCard);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String idCard);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkIdCard(String idCard);

    List<User> selectAllUsers();
}