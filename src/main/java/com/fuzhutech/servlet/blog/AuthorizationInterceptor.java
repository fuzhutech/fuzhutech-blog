package com.fuzhutech.servlet.blog;

import com.alibaba.fastjson.JSON;
import com.fuzhutech.common.ResponseResult;
import com.fuzhutech.service.blog.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("拦截器:URL:{},URI():{},method:{}",request.getRequestURL(),request.getRequestURI(),request.getMethod());

        String uri = request.getRequestURI();
        String method = request.getMethod();

        if(uri.startsWith("/api/posts")){
            if(!("GET").equals(method))
                return checkToken(request,response,handler);
        }else if (uri.startsWith("/api/comments")){
            if(("DELETE").equals(method))
                return checkToken(request,response,handler);
        }

        return  true;
    }

    private boolean checkToken(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        ResponseResult responseResult = new ResponseResult();
        //从header中得到token
        String authorization = request.getHeader("Authorization");
        //验证token
        if(userService.checkToken(authorization)){
            return true;
        }
        else{
            //返回401错误
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter print = response.getWriter();
            responseResult.setStatus(ResponseResult.FAILURE);
            responseResult.setMessage("AuthorizationInterceptor验证失败");
            String data = JSON.toJSONString(responseResult);
            print.print(data);

            return false;
        }
    }

}
