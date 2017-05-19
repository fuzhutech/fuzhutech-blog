package com.fuzhutech.controller.blog;

import com.baidu.ueditor.ActionEnter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
//@RequestMapping("/api")
public class UEditorController {

    private static Logger logger = LoggerFactory.getLogger(UEditorController.class);

    @RequestMapping("/ueditor")
    public void exec(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html");
            String rootPath = request.getSession().getServletContext().getRealPath("/");

            String configPath = this.getClass().getClassLoader().getResource("ueditor/config.json").getPath();
            response.getWriter().write(new ActionEnter(request, rootPath, configPath).exec());
        } catch (Exception e) {
            logger.error("百度富文本请求执行过程中发生异常:{}", e.getMessage());
        }
    }

}
