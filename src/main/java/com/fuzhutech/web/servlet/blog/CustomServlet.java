package com.fuzhutech.web.servlet.blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomServlet extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(CustomServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        logger.info("CustomServlet doGet URL:{}",req.getRequestURL());

        
        //如果是robot,则转发到/view/index
        //如果是angular2,则转发到/index.html
        RequestDispatcher rd = req.getRequestDispatcher("/index.html");
        rd.forward(req, resp);
    }
}
