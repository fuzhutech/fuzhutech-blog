package com.fuzhutech.util.blog;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class UserAgentUtils {

    private static final String DEFAULT_ROBOT_USER_AGENTS = "Googlebot|Mediapartners-Google|AdsBot-Google|bingbot|Baiduspider|yahooseeker|spider";
    private static String[] robotUserAgents = DEFAULT_ROBOT_USER_AGENTS.split("\\|");

    private UserAgentUtils() {
        //
    }

    public static void initCustomizeAgents(String robotUserAgentString) {
        if (!StringUtils.isEmpty(robotUserAgentString)) {
            robotUserAgents = robotUserAgentString.split("\\|");
        }
    }

    public static boolean isRobot(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent == null) {
            return false;
        }

        for (String key : robotUserAgents) {
            if (userAgent.contains(key)) {
                return true;
            }
        }

        return false;
    }
}
