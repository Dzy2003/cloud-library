package com.duan.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duan.library.domain.User;
import com.duan.library.service.UserService;
import com.duan.library.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author soga
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-12-24 14:18:11
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Override
    public User login(User loginUser) {
        return lambdaQuery()
                .eq(User::getEmail, loginUser.getEmail())
                .eq(User::getPassword, loginUser.getPassword())
                .one();
    }
}




