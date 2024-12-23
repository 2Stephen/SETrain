package com.whut.backend.POJO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserData {
    private String username;
    private String password;
    public UserData() {
    }
    public UserData(String username, String password) {
        this.username = username;
        this.password = password;
    }
    @Override
    public String toString() {
        return "UserData{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
