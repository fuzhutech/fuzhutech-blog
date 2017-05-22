package com.fuzhutech.service.blog;

import com.fuzhutech.common.service.BaseService;
import com.fuzhutech.entity.blog.User;

public interface UserService extends BaseService<User> {

    String createToken(User user);

    boolean checkToken(String token);

}
