package com.zxks.dao;

import com.zxks.pojo.Admin;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer idAdmin);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer idAdmin);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}