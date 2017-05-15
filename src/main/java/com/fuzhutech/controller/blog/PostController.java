package com.fuzhutech.controller.blog;

import com.fuzhutech.pojo.blog.Post;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 文章
@RestController
@RequestMapping("/posts")
public class PostController extends BlogRestfulController<Post> {

  @Override
  protected Integer getModelId(Post model) {
    return model.getId();
  }

}
