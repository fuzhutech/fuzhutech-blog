package com.fuzhutech.servlet.blog;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* 给log4j设置环境变量，必须要在jvm加载log4j.properties前设置 */
        String path = sce.getServletContext().getRealPath("/");
        System.setProperty("log4jHome", path);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
