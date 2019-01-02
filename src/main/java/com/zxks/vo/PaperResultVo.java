package com.zxks.vo;

public class PaperResultVo {

    private String[] answers;

    private String[] trueAnswers;

    private int scoreExam;

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public String[] getTrueAnswers() {
        return trueAnswers;
    }

    public void setTrueAnswers(String[] trueAnswers) {
        this.trueAnswers = trueAnswers;
    }

    public int getScoreExam() {
        return scoreExam;
    }

    public void setScoreExam(int scoreExam) {
        this.scoreExam = scoreExam;
    }
}
