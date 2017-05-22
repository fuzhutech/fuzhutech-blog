package com.fuzhutech.controller.blog;

import com.fuzhutech.entity.blog.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 用户
@RestController
@RequestMapping("/users")
public class UserController extends BlogRestfulController<User> {

}
