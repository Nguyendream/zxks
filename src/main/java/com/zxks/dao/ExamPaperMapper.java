package com.zxks.dao;

import com.zxks.pojo.ExamPaper;

public interface ExamPaperMapper {
    int deleteByPrimaryKey(Integer idPaper);

    int insert(ExamPaper record);

    int insertSelective(ExamPaper record);

    ExamPaper selectByPrimaryKey(Integer idPaper);

    int updateByPrimaryKeySelective(ExamPaper record);

    int updateByPrimaryKeyWithBLOBs(ExamPaper record);

    int updateByPrimaryKey(ExamPaper record);

    int checkPaper(String idCard);

    ExamPaper selectByIdCard(String idCard);

    int updateByIdCardSelective(ExamPaper examPaper);
}