package com.zxks.pojo;

public class ExamLibrary {
    private Integer idQuestion;

    private String codeSubject;

    private String question;

    private String answer;

    public ExamLibrary(Integer idQuestion, String codeSubject, String question, String answer) {
        this.idQuestion = idQuestion;
        this.codeSubject = codeSubject;
        this.question = question;
        this.answer = answer;
    }

    public ExamLibrary() {
        super();
    }

    public Integer getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Integer idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getCodeSubject() {
        return codeSubject;
    }

    public void setCodeSubject(String codeSubject) {
        this.codeSubject = codeSubject == null ? null : codeSubject.trim();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }
}