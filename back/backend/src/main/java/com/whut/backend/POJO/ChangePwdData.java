package com.whut.backend.POJO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePwdData {
    private String email;
    private String password;
    private String captcha;
    public ChangePwdData() {
    }
    public ChangePwdData(String email, String password, String captcha) {
        this.email = email;
        this.password = password;
        this.captcha = captcha;
    }
    @Override
    public String toString() {
        return "ChangePwdData{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", captcha='" + captcha + '\'' +
                '}';
    }
}
