package com.fuzhutech.controller.blog;

import com.fuzhutech.pojo.blog.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 用户
@RestController
@RequestMapping("/users")
public class UserController extends BlogRestfulController<User> {

  @Override
  protected Integer getModelId(User model) {
    return model.getId();
  }

}
