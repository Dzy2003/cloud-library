package com.duan.library.service;

import com.duan.library.domain.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import com.duan.library.domain.User;
import com.duan.library.entity.PageResult;

/**
* @author soga
* @description 针对表【book】的数据库操作Service
* @createDate 2023-12-24 21:24:09
*/
public interface BookService extends IService<Book> {

    Book findById(String id);

    Integer borrowBook(Book book);

    PageResult<Book> search(Book book, Integer pageNum, Integer pageSize);

    Integer addBook(Book book);

    Integer editBook(Book book);

    PageResult<Book> searchBorrowed(Book book, User user, Integer pageNum, Integer pageSize);

    boolean returnBook(String id, User user);

    Integer returnConfirm(String id);

}
