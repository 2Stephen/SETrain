package com.whut.backend.POJO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterData {
    private String username;
    private String password;
    private String email;
    private  String captcha;
    public RegisterData() {
    }
    public RegisterData(String username, String password, String email,String captcha) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.captcha = captcha;

    }
    @Override
    public String toString() {
        return "RegisterData{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", captcha='" + captcha + '\'' +
                '}';
    }
}
