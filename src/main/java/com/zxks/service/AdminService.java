package com.zxks.service;

import com.zxks.common.ServerResponse;
import com.zxks.pojo.*;

import java.util.List;

public interface AdminService {

    ServerResponse<Admin> login(String username, String password);

    //用户管理
    ServerResponse<List<User>> getUserList();

    ServerResponse<String> addUser(User user);

    ServerResponse<String> updateUserByIdCard(User user);

    ServerResponse<String> deleteUserByIdCard(String idCard);

    //试卷管理
    ServerResponse<List<ExamPaper>> getPaperListNoData();//不获取试卷内容

    ServerResponse<ExamPaper> getPaperByIdCard(String idCard);

    //题库管理
    ServerResponse<List<ExamLibrary>> getLibraryList();

    ServerResponse<String> addQuestion(ExamLibrary examLibrary);

    ServerResponse<String> updateQuestionById(ExamLibrary examLibrary);

    ServerResponse<String> deleteQuestionById(Integer id);

    //考试参数
    ServerResponse<List<ExamParameter>> getParameterList();

    ServerResponse<String> addSubject(ExamParameter examParameter);

    ServerResponse<String> updateSubjectByCode(ExamParameter examParameter);

    ServerResponse<String> deleteSubjectByCode(String codeSubject);

}
