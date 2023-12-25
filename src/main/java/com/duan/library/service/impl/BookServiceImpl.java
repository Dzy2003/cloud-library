package com.duan.library.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duan.library.domain.Book;
import com.duan.library.domain.Record;
import com.duan.library.domain.User;
import com.duan.library.entity.PageResult;
import com.duan.library.service.BookService;
import com.duan.library.mapper.BookMapper;
import com.duan.library.service.RecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @author soga
* @description 针对表【book】的数据库操作Service实现
* @createDate 2023-12-24 21:24:09
*/
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book>
    implements BookService{
    @Resource
    RecordService recordService;

    @Override
    public Book findById(String id) {
        return lambdaQuery()
                .eq(Book::getId, id)
                .one();
    }

    @Override
    public Integer borrowBook(Book book) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return lambdaUpdate()
                .eq(Book::getId, book.getId())
                .set(Book::getBorrower,book.getBorrower())
                .set(Book::getReturnTime, book.getReturnTime())
                .set(Book::getBorrowTime, dateFormat.format(new Date()))
                .set(Book::getStatus, "1")
                .update() ? 1 : 0;
    }

    @Override
    public PageResult<Book> search(Book book, Integer pageNum, Integer pageSize) {
        Page<Book> page = lambdaQuery()
                .like(book.getAuthor() != null,Book::getAuthor, "%" + book.getAuthor() + "%")
                .like(book.getName() != null,Book::getName, "%" + book.getName() + "%")
                .like(book.getPress() != null, Book::getPress, "%" + book.getPress() + "%")
                .page(new Page<>(pageNum, pageSize));
        return new PageResult<>(page.getTotal(), page.getRecords());
    }

    @Override
    public Integer addBook(Book book) {
        return save(book) ? 1 : 0;
    }

    @Override
    public Integer editBook(Book book) {
        return lambdaUpdate()
                .eq(Book::getId, book.getId())
                .update(book) ? 1 : 0;
    }

    @Override
    public PageResult<Book> searchBorrowed(Book book, User user, Integer pageNum, Integer pageSize) {
        if("ADMIN".equals(user.getRole())){
            Page<Book> page = lambdaQuery()
                    .eq(Book::getStatus, "1")
                    .or()
                    .eq(Book::getStatus, "2")
                    .orderByDesc(Book::getUploadTime)
                    .page(new Page<>(pageNum, pageSize));
            return new PageResult<>(page.getTotal(),page.getRecords());
        }else {
            //如果是普通用户，查询自己借阅但未归还的图书
            Page<Book> page = lambdaQuery()
                    .eq(Book::getBorrower, user.getName())
                    .in(Book::getStatus, "1", "2")
                    .orderByDesc(Book::getUploadTime)
                    .page(new Page<>(pageNum, pageSize));
            return new PageResult<>(page.getTotal(),page.getRecords());
        }
    }

    @Override
    public boolean returnBook(String id, User user) {
        Book book = lambdaQuery()
                .eq(Book::getId, id)
                .eq(Book::getBorrower, user.getName())
                .one();
        if(book == null){
            return false;
        }
        lambdaUpdate()
                .eq(Book::getId, id)
                .set(Book::getStatus, "2")
                .update();
        return true;

    }

    @Override
    public Integer returnConfirm(String id) {
        Book book = getById(id);
        Record record = setRecord(book);
        boolean succeed = lambdaUpdate()
                .eq(Book::getId, id)
                .set(Book::getStatus, "0")
                .set(Book::getBorrower, "")
                .set(Book::getBorrowTime, "")
                .set(Book::getReturnTime, "")
                .update();
        if(succeed){
            recordService.save(record);
            return 1;
        }
        return 0;
    }

    private Record setRecord(Book book){
        Record record = new Record();
        //设置借阅记录的图书名称
        record.setBookname(book.getName());
        //设置借阅记录的图书isbn
        record.setBookisbn(book.getIsbn());
        //设置借阅记录的借阅人
        record.setBorrower(book.getBorrower());
        //设置借阅记录的借阅时间
        record.setBorrowtime(book.getBorrowTime());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //设置图书归还确认的当天为图书归还时间
        record.setRemandtime(dateFormat.format(new Date()));
        return record;
    }
}




