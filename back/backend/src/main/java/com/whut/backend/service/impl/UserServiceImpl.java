package com.whut.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whut.backend.entity.User;
import com.whut.backend.mapper.UserMapper;
import com.whut.backend.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public boolean login(String username, String password) {
        User user = baseMapper.login(username, password);
        if(user != null) {
            return true;
        }
        return false;
    }
}
