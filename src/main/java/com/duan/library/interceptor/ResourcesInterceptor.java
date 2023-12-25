package com.duan.library.interceptor;



import com.duan.library.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 资源拦截器
 */
public class ResourcesInterceptor implements HandlerInterceptor {
    private List<String> ignoreUrl;
    public ResourcesInterceptor(List<String> ignoreUrl) {
        this.ignoreUrl = ignoreUrl;
    }
    //任意角色都能访问的路径
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        User user = (User) request.getSession().getAttribute("USER_SESSION");
        //获取请求的路径
        String uri = request.getRequestURI();
        if(user == null){
            request.setAttribute("msg", "您还没有登录，请先登录！");
            request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
            return false;
        }
        //如果用户是已登录状态，判断访问的资源是否有权限
            //如果是管理员，放行
        if ("ADMIN".equals(user.getRole())) {
                return true;
        }else{
            for (String url : ignoreUrl) {
                //访问的资源不是管理员权限的资源，放行
                if (uri.contains(url)) {
                    return true;
                }
            }
        }
        //对用户登录的相关请求，放行
        return uri.contains("login");
    }
}