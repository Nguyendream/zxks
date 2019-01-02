package com.zxks.common;

public class Const {

    public static final String CURRENT_USER = "currentUser";
    public static final String CURRENT_ADMIN = "currentAdmin";
    public static final String USERNAME = "username";
    public static final String CURRENT_EXAM = "currentExam";

    public interface Role {
        int ROLE_USER = 0;  //普通用户
        int ROLE_ADMIN = 1; //管理员
    }

    public interface ExamErrorCode {
        int NULL = 2;
        int CREATE_ERROR = 3;
        int EXAM_FINISHED = 4;
        int PARAMETER_ERROR = 5;
    }

}
