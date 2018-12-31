package com.zxks.pojo;

public class User {
    private String idCard;

    private String username;

    private String password;

    private String sex;

    private Integer age;

    public User(String idCard, String username, String password, String sex, Integer age) {
        this.idCard = idCard;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.age = age;
    }

    public User() {
        super();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}