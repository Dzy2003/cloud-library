package com.duan.library.config;

import com.duan.library.interceptor.ResourcesInterceptor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.servlet.config.annotation.*;

import java.util.Arrays;

/**
 * @author 白日
 * @create 2023/12/24 18:38
 * @description
 */
@Configurable
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登录拦截器
        registry.addInterceptor(new ResourcesInterceptor(
                Arrays.stream(("/logout,/selectNewbooks,/findById,/borrowBook,/search," +
                        "/searchBorrowed,/returnBook,/searchRecords")
                        .split(",")).toList()
        )).addPathPatterns("/**").excludePathPatterns(
                "/css/**",
                "/js/**",
                "/img/**",
                "");
    }
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/admin/",".jsp");
    }
}
