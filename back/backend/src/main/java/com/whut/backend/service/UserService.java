package com.whut.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.whut.backend.entity.User;

public interface UserService extends IService<User> {
    // login
    String login(String username, String password);
    // register
    boolean register(String username, String password, String email, String role);
    // 获取是否存在邮箱
    boolean containsEmail(String email);
    // 修改密码
    boolean changePwd(String email, String password);


}
