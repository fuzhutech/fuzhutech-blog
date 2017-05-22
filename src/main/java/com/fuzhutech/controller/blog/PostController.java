package com.fuzhutech.controller.blog;

import com.fuzhutech.entity.blog.Post;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 文章
@RestController
@RequestMapping("/posts")
public class PostController extends BlogRestfulController<Post> {

}
