package com.test.react_spring.controller;

public class LoginForm {

    private String id;
    private String pw;

    public LoginForm(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    public String getPw() {
        return pw;
    }

    public String getId() {
        return id;
    }
}
