package com.duan.library.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duan.library.domain.User;
import com.duan.library.entity.PageResult;
import com.duan.library.service.UserService;
import com.duan.library.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Override
    public void addUser(User user) {
        user.setStatus("0");
        save(user);
    }

    @Override
    public void delUser(Integer id) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        lambdaUpdate()
                .eq(User::getId, id)
                .set(User::getStatus, "1")
                .set(User::getDeparturedate, dateFormat.format(new Date()));

    }

    @Override
    public void editUser(User user) {
        lambdaUpdate()
                .eq(User::getId, user.getId())
                .update(user);
    }

    @Override
    public PageResult<User> searchUsers(User user, Integer pageNum, Integer pageSize) {
        Page<User> page = lambdaQuery()
                .like(user.getName() != null, User::getName, user.getName())
                .orderByAsc(User::getStatus)
                .page(new Page<>(pageNum, pageSize));
        return new PageResult<>(page.getTotal(), page.getRecords());
    }

    @Override
    public User findById(Integer id) {
        return getById(id);
    }

    @Override
    public Long checkName(String name) {
        return lambdaQuery()
                .eq(User::getName, name)
                .count();
    }

    @Override
    public Long checkEmail(String email) {
        return lambdaQuery()
                .eq(User::getEmail, email)
                .count();
    }
}




