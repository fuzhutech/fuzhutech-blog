package com.fuzhutech.controller.blog;

import com.fuzhutech.entity.blog.Comment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 文章评论
@RestController
@RequestMapping("/comments")
public class CommentController extends BlogRestfulController<Comment> {

    @Override
    protected Integer getModelId(Comment model) {
        return model.getId();
    }

}
