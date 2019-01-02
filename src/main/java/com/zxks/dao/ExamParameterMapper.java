package com.zxks.dao;

import com.zxks.pojo.ExamParameter;

import java.util.List;

public interface ExamParameterMapper {
    int deleteByPrimaryKey(String codeSubject);

    int insert(ExamParameter record);

    int insertSelective(ExamParameter record);

    ExamParameter selectByPrimaryKey(String codeSubject);

    int updateByPrimaryKeySelective(ExamParameter record);

    int updateByPrimaryKey(ExamParameter record);

    List<ExamParameter> selectList();
}