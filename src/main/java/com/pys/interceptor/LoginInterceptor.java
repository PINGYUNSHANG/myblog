package com.pys.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//配置拦截器
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        //判断session是否有用户
        if(request.getSession().getServletContext().getAttribute("user")==null){
            response.sendRedirect("/admin");//重定向
            return  false;//拦截请求
        }
        return  true;
    }
}
