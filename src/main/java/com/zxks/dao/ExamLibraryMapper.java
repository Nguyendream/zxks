package com.zxks.dao;

import com.zxks.pojo.ExamLibrary;

public interface ExamLibraryMapper {
    int deleteByPrimaryKey(Integer idQuestion);

    int insert(ExamLibrary record);

    int insertSelective(ExamLibrary record);

    ExamLibrary selectByPrimaryKey(Integer idQuestion);

    int updateByPrimaryKeySelective(ExamLibrary record);

    int updateByPrimaryKey(ExamLibrary record);
}