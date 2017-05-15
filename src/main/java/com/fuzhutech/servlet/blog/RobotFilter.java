package com.fuzhutech.servlet.blog;

import com.fuzhutech.util.blog.UserAgentUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RobotFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(RobotFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        UserAgentUtils.initCustomizeAgents(null);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String url = req.getRequestURL().toString();
        String uri = req.getRequestURI();
        String userAgent = req.getHeader("User-Agent");

        logger.info("RobotFilter:URL: {}，Method:{},URI: {},userAgent: {}", url, req.getMethod(), uri, userAgent);

        if (StringUtils.equals(uri, "/")) {
            if (UserAgentUtils.isRobot(req) && isTextRequest(req)) {
                logger.info("Load static html for robot: " + (req.getRequestURL().toString() + "?" + req.getQueryString()));
                req.getRequestDispatcher("/view/index").forward(request, response);
                return;
            } else {
                req.getRequestDispatcher("/index.html").forward(request, response);
                return;
            }
        }

        if (StringUtils.startsWith(uri, "/p/")) {
            if (UserAgentUtils.isRobot(req) && isTextRequest(req)) {
                logger.info("Load static html for robot: " + (req.getRequestURL().toString() + "?" + req.getQueryString()));
                String path = StringUtils.replace(uri, "/p/", "/view/post/");
                req.getRequestDispatcher(path).forward(request, response);
                return;
            } else {
                req.getRequestDispatcher("/index.html").forward(request, response);
                return;
            }
        }

        //sitemap.xml
        if(StringUtils.equals(uri,"/sitemap.xml")){
            req.getRequestDispatcher("/view/sitemap.xml").forward(request, response);
            return;
        }

        //Content-Type:text/html;charset=UTF-8
        //Content-Type:application/json;charset=UTF-8
        //解决历史遗留文章链接问题
        if (StringUtils.startsWith(uri, "/posts/")) {
            String id = StringUtils.substring(uri, StringUtils.length("/posts/")).replace("/","");
            logger.info("id:{}",id);
            String redirectId = null;
            if (StringUtils.equals(id, "6")) {
                redirectId = "1";
            } else if (StringUtils.equals(id, "17")) {
                redirectId = "2";
            } else if (StringUtils.equals(id, "22")) {
                redirectId = "3";
            } else if (StringUtils.equals(id, "24")) {
                redirectId = "4";
            } else if (StringUtils.equals(id, "25")) {
                redirectId = "5";
            }else if (StringUtils.equals(id, "26")) {
                redirectId = "6";
            } else if (StringUtils.equals(id, "27")) {
                redirectId = "8";
            } else if (StringUtils.equals(id, "29")) {
                redirectId = "10";
            }
            logger.info("redirectId:{}",redirectId);
            if (redirectId != null) {
                String redirectUrl = "/p/" + redirectId;
                logger.info("redirectId:{},redirectUrl:{}",redirectId,redirectUrl);
                ((HttpServletResponse) response).sendRedirect(redirectUrl);
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //
    }

    /**
     * Check if the request is for html page as far as possible
     */
    private boolean isTextRequest(HttpServletRequest request) {
        String uri = request.getRequestURI();
        int p = uri.lastIndexOf("/");
        // requst for site default page
        if (p < 0) {
            return true;
        }

        String file = uri.substring(p + 1);
        p = file.indexOf(".");
        // without extention, usually is a html request
        if (p < 0) {
            return true;
        }

        String ext = file.substring(p + 1);
        return "html".equals(ext) || "htm".equals(ext) || "jsp".equals(ext);

    }

}
