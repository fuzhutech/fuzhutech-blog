package com.fuzhutech.service.blog.imp;


import com.fuzhutech.common.service.impl.BaseServiceImpl;
import com.fuzhutech.pojo.blog.User;
import com.fuzhutech.service.blog.UserService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public String createToken(User user){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String token =  formatter.format(user.getLastLoginTime());
        token = user.getId() + "," + token;
        return Base64.encodeBase64String(org.apache.commons.codec.binary.StringUtils.getBytesUtf8(token));
    }

    @Override
    public boolean checkToken(String token) {
        if(StringUtils.isEmpty(token))
            return  false;

        String str = org.apache.commons.codec.binary.StringUtils.newStringUtf8(Base64.decodeBase64(token));
        int i = str.indexOf(',');
        String userId = str.substring(0, i);
        String loginTime = str.substring(i+1);

        if (!StringUtils.isNumeric(userId))
            return false;
        User record = new User();
        record.setId(Integer.parseInt(userId));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            record.setLastLoginTime(formatter.parse(loginTime));

            List<User> list = this.queryListByWhere(record);
            if(list.isEmpty())
                return false;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
