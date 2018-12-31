package com.zxks.pojo;

public class ExamParameter {
    private String codeSubject;

    private String nameSubject;

    private Integer timeExam;

    private Integer scoreTotal;

    private Integer amountQuestion;

    public ExamParameter(String codeSubject, String nameSubject, Integer timeExam, Integer scoreTotal, Integer amountQuestion) {
        this.codeSubject = codeSubject;
        this.nameSubject = nameSubject;
        this.timeExam = timeExam;
        this.scoreTotal = scoreTotal;
        this.amountQuestion = amountQuestion;
    }

    public ExamParameter() {
        super();
    }

    public String getCodeSubject() {
        return codeSubject;
    }

    public void setCodeSubject(String codeSubject) {
        this.codeSubject = codeSubject == null ? null : codeSubject.trim();
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject == null ? null : nameSubject.trim();
    }

    public Integer getTimeExam() {
        return timeExam;
    }

    public void setTimeExam(Integer timeExam) {
        this.timeExam = timeExam;
    }

    public Integer getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(Integer scoreTotal) {
        this.scoreTotal = scoreTotal;
    }

    public Integer getAmountQuestion() {
        return amountQuestion;
    }

    public void setAmountQuestion(Integer amountQuestion) {
        this.amountQuestion = amountQuestion;
    }
}