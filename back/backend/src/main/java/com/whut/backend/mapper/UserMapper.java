package com.whut.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whut.backend.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User>{
    User login(@Param("username") String username,@Param("password") String password);
}