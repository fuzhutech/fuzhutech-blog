package com.fuzhutech.service.blog;

import com.fuzhutech.pojo.blog.User;
import com.fuzhutech.common.service.BaseService;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public interface UserService extends BaseService<User> {

    String createToken(User user);

    boolean checkToken(String token);

}
