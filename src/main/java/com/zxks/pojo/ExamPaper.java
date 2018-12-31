package com.zxks.pojo;

public class ExamPaper {
    private Integer idPaper;

    private String idCard;

    private String codeSubject;

    private Integer scoreExam;

    private String dataPaper;

    public ExamPaper(Integer idPaper, String idCard, String codeSubject, Integer scoreExam) {
        this.idPaper = idPaper;
        this.idCard = idCard;
        this.codeSubject = codeSubject;
        this.scoreExam = scoreExam;
    }

    public ExamPaper(Integer idPaper, String idCard, String codeSubject, Integer scoreExam, String dataPaper) {
        this.idPaper = idPaper;
        this.idCard = idCard;
        this.codeSubject = codeSubject;
        this.scoreExam = scoreExam;
        this.dataPaper = dataPaper;
    }

    public ExamPaper() {
        super();
    }

    public Integer getIdPaper() {
        return idPaper;
    }

    public void setIdPaper(Integer idPaper) {
        this.idPaper = idPaper;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getCodeSubject() {
        return codeSubject;
    }

    public void setCodeSubject(String codeSubject) {
        this.codeSubject = codeSubject == null ? null : codeSubject.trim();
    }

    public Integer getScoreExam() {
        return scoreExam;
    }

    public void setScoreExam(Integer scoreExam) {
        this.scoreExam = scoreExam;
    }

    public String getDataPaper() {
        return dataPaper;
    }

    public void setDataPaper(String dataPaper) {
        this.dataPaper = dataPaper == null ? null : dataPaper.trim();
    }
}