package com.whut.backend.POJO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterData {
    private String username;
    private String password;
    private String email;
    public RegisterData() {
    }
    public RegisterData(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    @Override
    public String toString() {
        return "RegisterData{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
