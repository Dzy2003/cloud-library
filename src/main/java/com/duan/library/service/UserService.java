package com.duan.library.service;

import com.duan.library.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.duan.library.entity.PageResult;

/**
* @author soga
* @description 针对表【user】的数据库操作Service
* @createDate 2023-12-24 15:29:42
*/
public interface UserService extends IService<User> {

    User login(User loginUser);

    void addUser(User user);

    void delUser(Integer id);

    void editUser(User user);

    PageResult<User> searchUsers(User user, Integer pageNum, Integer pageSize);

    User findById(Integer id);

    Long checkName(String name);

    Long checkEmail(String email);

}
