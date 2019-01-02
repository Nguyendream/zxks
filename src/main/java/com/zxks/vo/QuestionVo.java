package com.zxks.vo;

public class QuestionVo {

    private Integer idQuestion;

    private String codeSubject;

    private String question;

    private String trueAnswer;

    private String answer;

    public QuestionVo(Integer idQuestion, String codeSubject, String question, String trueAnswer, String answer) {
        this.idQuestion = idQuestion;
        this.codeSubject = codeSubject;
        this.question = question;
        this.trueAnswer = trueAnswer;
        this.answer = answer;
    }

    public QuestionVo() {
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
        this.codeSubject = codeSubject;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(String trueAnswer) {
        this.trueAnswer = trueAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
