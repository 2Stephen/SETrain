package com.whut.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.whut.backend.entity.User;

public interface UserService extends IService<User> {
    // login
    boolean login(String username, String password);

}
