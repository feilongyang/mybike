package com.yang.springtest.domain;

import javax.validation.constraints.Size;

public class User {

    @Size(min = 3,max = 7,message = "用户名的长度在3-7个字符")
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
