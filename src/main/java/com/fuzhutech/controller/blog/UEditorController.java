package com.fuzhutech.controller.blog;

import com.fuzhutech.ueditor.ActionEnter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UEditorController {

    @RequestMapping("/ueditor")
    public void exec(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html");
            String rootPath = request.getSession().getServletContext().getRealPath("/");

            String configPath = this.getClass().getClassLoader().getResource("ueditor/config.json").getPath();
            response.getWriter().write(new ActionEnter(request, rootPath,configPath).exec());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
