package com.whut.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whut.backend.entity.User;
import com.whut.backend.mapper.UserMapper;
import com.whut.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public boolean login(String username, String password) {

        //MD5签名前
        log.info("MD5加密前：{}", password);
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //MD5签名后
        log.info("MD5加密后：{}", password);
        //123465 --> e10adc3949ba59abbe56e057f20f883e

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
                //MD5签名
                password = DigestUtils.md5DigestAsHex(password.getBytes());
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
            //MD5签名
            password = DigestUtils.md5DigestAsHex(password.getBytes());
            int res = userMapper.updateById(user.getId(), password);
            return res == 1;
        }
        return false;
    }
}
