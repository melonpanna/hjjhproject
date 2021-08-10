package com.test.react_spring.domain;

import java.io.Serializable;

public class MemberVO implements Serializable {
    private String id;
    private String pw;
    private String name;
    private String email;

    public MemberVO(String id, String pw, String name, String email) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.email = email;
    }


    //private int role;
}
