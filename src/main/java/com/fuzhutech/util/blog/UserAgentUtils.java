package com.fuzhutech.util.blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class UserAgentUtils {
    private static Logger logger = LoggerFactory.getLogger(UserAgentUtils.class);

    private static String DEFAULT_ROBOT_USER_AGENTS = "Googlebot|Mediapartners-Google|AdsBot-Google|bingbot|Baiduspider|yahooseeker|spider";
    private static String[] ROBOT_USER_AGENTS = new String[]{};

    public static void initCustomizeAgents(String robotUserAgent) {

        String allAgents = DEFAULT_ROBOT_USER_AGENTS;
        if (StringUtils.isEmpty(robotUserAgent)) {
            allAgents += robotUserAgent;
            DEFAULT_ROBOT_USER_AGENTS = allAgents;
        }

        ROBOT_USER_AGENTS = allAgents.split("\\|");
    }

    public static boolean isRobot(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent == null) {
            return false;
        }

        for (String key : ROBOT_USER_AGENTS) {
            if (userAgent.contains(key)) {
                return true;
            }
        }

        return false;
    }
}
