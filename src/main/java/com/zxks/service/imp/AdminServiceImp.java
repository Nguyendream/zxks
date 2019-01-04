package com.zxks.service.imp;

import com.zxks.common.ServerResponse;
import com.zxks.dao.AdminMapper;
import com.zxks.pojo.*;
import com.zxks.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("AdminService")
public class AdminServiceImp implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public ServerResponse<Admin> login(String username, String password) {
        return null;
    }

    @Override
    public ServerResponse<List<User>> getUserList() {
        return null;
    }

    @Override
    public ServerResponse<String> addUser(User user) {
        return null;
    }

    @Override
    public ServerResponse<String> updateUserByIdCard(User user) {
        return null;
    }

    @Override
    public ServerResponse<String> deleteUserByIdCard(String idCard) {
        return null;
    }

    @Override
    public ServerResponse<List<ExamPaper>> getPaperListNoData() {
        return null;
    }

    @Override
    public ServerResponse<ExamPaper> getPaperByIdCard(String idCard) {
        return null;
    }

    @Override
    public ServerResponse<List<ExamLibrary>> getLibraryList() {
        return null;
    }

    @Override
    public ServerResponse<String> addQuestion(ExamLibrary examLibrary) {
        return null;
    }

    @Override
    public ServerResponse<String> updateQuestionById(ExamLibrary examLibrary) {
        return null;
    }

    @Override
    public ServerResponse<String> deleteQuestionById(Integer id) {
        return null;
    }

    @Override
    public ServerResponse<List<ExamParameter>> getParameterList() {
        return null;
    }

    @Override
    public ServerResponse<String> addSubject(ExamParameter examParameter) {
        return null;
    }

    @Override
    public ServerResponse<String> updateSubjectByCode(ExamParameter examParameter) {
        return null;
    }

    @Override
    public ServerResponse<String> deleteSubjectByCode(String codeSubject) {
        return null;
    }
}
