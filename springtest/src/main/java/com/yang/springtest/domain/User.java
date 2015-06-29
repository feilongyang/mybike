package com.yang.springtest.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {

    @Size(min = 3,max = 7,message = "用户名的长度在3-7个字符")
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Size(min = 6,message = "密码最少6位")
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
