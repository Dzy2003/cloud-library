package com.duan.library.mapper;

import com.duan.library.domain.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author soga
* @description 针对表【book】的数据库操作Mapper
* @createDate 2023-12-24 21:24:09
* @Entity com.duan.library.domain.Book
*/
@Mapper
public interface BookMapper extends BaseMapper<Book> {

}




