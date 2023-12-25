package com.duan.library.mapper;

import com.duan.library.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author soga
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-12-24 15:29:42
* @Entity com.duan.library.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




