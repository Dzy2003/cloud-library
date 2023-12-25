package com.duan.library.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.duan.library.domain.Book;
import com.duan.library.domain.User;
import com.duan.library.entity.PageResult;
import com.duan.library.entity.result;
import com.duan.library.service.impl.BookServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * @author 白日
 * @create 2023/12/24 14:21
 * @description
 */
@Controller
@RequestMapping("/book")
public class BookController {
    @Resource
    private BookServiceImpl bookService;

    @RequestMapping("/selectNewbooks")
    public String selectNewbooks(HttpServletRequest request) {
        //查询最新上架的5个的图书信息
        int pageNum = 1;
        int pageSize = 5;
        List<Book> records = bookService
                .lambdaQuery()
                .orderByAsc(Book::getUploadTime)
                .ne(Book::getStatus, "3")
                .page(new Page<>(pageNum, pageSize))
                .getRecords();
        request.setAttribute("pageResult", records);
        return "forward:/admin/books_new.jsp";
    }

    @ResponseBody
    @GetMapping("/findById")
    public result<Book> findById(String id) {
        try {
            Book book = bookService.findById(id);
            if (book == null) {
                return new result<>(false, "查询图书失败！");
            }
            return new result<>(true, "查询图书成功", book);
        } catch (Exception e) {
            e.printStackTrace();
            return new result<>(false, "查询图书失败！");
        }
    }

    /**
     * 借阅图书
     *
     * @param book 借阅的图书
     * @return
     */
    @ResponseBody
    @RequestMapping("/borrowBook")
    public result borrowBook(Book book, HttpSession session) {
        //获取当前登录的用户姓名
        String pname = ((User) session.getAttribute("USER_SESSION")).getName();
        book.setBorrower(pname);
        try {
            //根据图书的id和用户进行图书借阅
            Integer count = bookService.borrowBook(book);
            if (count != 1) {
                return new result<>(false, "借阅图书失败!");
            }
            return new result<>(true, "借阅成功，请到行政中心取书!");
        } catch (Exception e) {
            e.printStackTrace();
            return new result<>(false, "借阅图书失败!");
        }
    }

    /**
     * 分页查询符合条件且未下架图书信息
     *
     * @param book     查询的条件封装到book中
     * @param pageNum  数据列表的当前页码
     * @param pageSize 数据列表1页展示多少条数据
     */
    @RequestMapping("/search")
    public String search(Book book, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        //查询到的图书信息
        PageResult<Book> pageResult = bookService.search(book, pageNum, pageSize);
        ModelAndView modelAndView = new ModelAndView();
        //将查询到的数据存放在 ModelAndView的对象中
        request.setAttribute("pageResult", pageResult);
        //将查询的参数返回到页面，用于回显到查询的输入框中
        request.setAttribute("search", book);
        //将当前页码返回到页面，用于分页插件的分页显示
        request.setAttribute("pageNum", pageNum);
        //将当前查询的控制器路径返回到页面，页码变化时继续向该路径发送请求
        request.setAttribute("gourl", request.getRequestURI());
        return "forward:/admin/books.jsp";
    }

    /**
     * 新增图书
     *
     * @param book 页面表单提交的图书信息
     *             将新增的结果和向页面传递信息封装到Result对象中返回
     */
    @ResponseBody
    @RequestMapping("/addBook")
    public result<Void> addBook(Book book) {
        try {
            Integer count = bookService.addBook(book);
            if (count != 1) {
                return new result<>(false, "新增图书失败!");
            }
            return new result<>(true, "新增图书成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new result<>(false, "新增图书失败!");
        }
    }

    /**
     * 编辑图书信息
     *
     * @param book 编辑的图书信息
     */
    @ResponseBody
    @RequestMapping("/editBook")
    public result<Void> editBook(Book book) {
        try {
            Integer count = bookService.editBook(book);
            if (count != 1) {
                return new result<Void>(false, "编辑失败!");
            }
            return new result<Void>(true, "编辑成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new result<Void>(false, "编辑失败!");
        }
    }

    /**
     * 分页查询当前被借阅且未归还的图书信息
     *
     * @param pageNum  数据列表的当前页码
     * @param pageSize 数据列表1页展示多少条数据
     */
    @RequestMapping("/searchBorrowed")
    public String searchBorrowed(Book book, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        //获取当前登录的用户
        User user = (User) request.getSession().getAttribute("USER_SESSION");
        PageResult<Book> pageResult = bookService.searchBorrowed(book, user, pageNum, pageSize);
        //将查询到的数据存放在 ModelAndView的对象中
        request.setAttribute("pageResult", pageResult);
        //将查询的参数返回到页面，用于回显到查询的输入框中
        request.setAttribute("search", book);
        //将当前页码返回到页面，用于分页插件的分页显示
        request.setAttribute("pageNum", pageNum);
        //将当前查询的控制器路径返回到页面，页码变化时继续向该路径发送请求
        request.setAttribute("gourl", request.getRequestURI());
        return "forward:/admin/book_borrowed.jsp";
    }

    /**
     * 归还图书
     *
     * @param id 归还的图书的id
     */
    @ResponseBody
    @RequestMapping("/returnBook")
    public result<Void> returnBook(String id, HttpSession session) {
        //获取当前登录的用户信息
        User user = (User) session.getAttribute("USER_SESSION");
        try {
            boolean flag = bookService.returnBook(id, user);
            if (!flag) {
                return new result<>(false, "还书失败!");
            }
            return new result<>(true, "还书确认中，请先到行政中心还书!");
        } catch (Exception e) {
            return new result<>(false, "还书失败!");
        }
    }

    /**
     * 确认图书归还
     *
     * @param id 确认归还的图书的id
     */
    @ResponseBody
    @RequestMapping("/returnConfirm")
    public result<Void> returnConfirm(String id) {
        try {
            Integer count = bookService.returnConfirm(id);
            if (count != 1) {
                return new result<>(false, "确认失败!");
            }
            return new result<>(true, "确认成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new result<>(false, "确认失败!");
        }

    }
}