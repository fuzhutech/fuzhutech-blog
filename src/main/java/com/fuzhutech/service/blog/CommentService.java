package com.fuzhutech.service.blog;

import com.fuzhutech.common.service.BaseService;
import com.fuzhutech.pojo.blog.Comment;

public interface CommentService extends BaseService<Comment> {
    int selectAllCount();
}
