package com.duan.library.Controller;

import com.duan.library.domain.User;
import com.duan.library.service.impl.UserServiceImpl;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @author 白日
 * @create 2023/12/24 14:20
 * @description
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserServiceImpl userService;
    @PostMapping("/login")
    public String login(String email, String password, HttpServletRequest request){
        try {
            User loginUser = new User();
            loginUser.setEmail(email);
            loginUser.setPassword(password);
            User user = userService.login(loginUser);
            /*
            用户账号和密码是否查询出用户信息
                是：将用户信息存入Session，并跳转到后台首页
                否：Request域中添加提示信息，并转发到登录页面
             */
            if (user != null) {
                request.getSession().setAttribute("USER_SESSION", user);
                String role = user.getRole();
                if ("ADMIN".equals(role)) {
                    return "redirect:/admin/main.jsp";
                } else {
                    return "redirect:/admin/index.jsp";
                }

            }
            request.setAttribute("msg", "用户名或密码错误");
            return "forward:/admin/login.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "系统错误");
            return "forward:/admin/login.jsp";
        }
    }
    /*
 注销登录
  */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        try {
            //销毁Session
            request.getSession().invalidate();
            return "forward:/admin/login.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "系统错误");
            return "forward:/admin/login.jsp";
        }
    }
}
