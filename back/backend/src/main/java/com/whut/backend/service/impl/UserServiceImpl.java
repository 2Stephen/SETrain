package com.whut.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whut.backend.entity.User;
import com.whut.backend.mapper.UserMapper;
import com.whut.backend.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public boolean login(String username, String password) {
        User user = userMapper.login(username, password);
        if(user != null) {
            return true;
        }
        return false;
    }
    @Override
    public boolean register(String username, String password, String email){
        User user = userMapper.findByUsername(username);
        if(user == null){
            user = userMapper.findByEmail(email);
            if(user == null) {
                user = new User();
                user.setEmail(email);
                user.setPassword(password);
                user.setUsername(username);
                int res = userMapper.insert(user);
                return res == 1;
            }
        }
        return false;
    }

    @Override
    public boolean containsEmail(String email) {
        User user = userMapper.findByEmail(email);
        return user != null;
    }

    @Override
    public boolean changePwd(String email, String password) {
        User user = userMapper.findByEmail(email);
        if(user != null) {
            int res = userMapper.updateById(user.getId(), password);
            return res == 1;
        }
        return false;
    }
}
