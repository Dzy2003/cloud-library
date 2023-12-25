package com.duan.library.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.duan.library.domain.Record;
import com.duan.library.domain.User;
import com.duan.library.entity.PageResult;
import com.duan.library.service.impl.RecordServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 白日
 * @create 2023/12/24 14:21
 * @description
 */
@Controller
@RequestMapping("/record")
public class RecordController {
    @Resource
    private RecordServiceImpl recordService;
    /**
     * 查询借阅记录
     * @param record 借阅记录的查询条件
     * @param pageNum 当前页码
     * @param pageSize 每页显示数量
     */
    @RequestMapping("/searchRecords")
    public String searchRecords(Record record, HttpServletRequest request, Integer pageNum, Integer pageSize){
        if(pageNum==null){
            pageNum=1;
        }
        if(pageSize==null){
            pageSize=10;
        }
        //获取当前登录用户的信息
        User user = ((User) request.getSession().getAttribute("USER_SESSION"));
        PageResult<Record> pageResult=recordService.searchRecords(record,user,pageNum,pageSize);
        //将查询到的数据存放在 ModelAndView的对象中
        request.setAttribute("pageResult",pageResult);
        //将查询的参数返回到页面，用于回显到查询的输入框中
        request.setAttribute("search",record);
        //将当前页码返回到页面，用于分页插件的分页显示
        request.setAttribute("pageNum",pageNum);
        //将当前查询的控制器路径返回到页面，页码变化时继续向该路径发送请求
        request.setAttribute("gourl", request.getRequestURI());
        return "forward:/admin/record.jsp";
    }
}
