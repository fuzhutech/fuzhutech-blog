package com.fuzhutech.dao.blog;

import com.fuzhutech.common.dao.BaseMapper;
import com.fuzhutech.entity.blog.Comment;

public interface CommentMapper extends BaseMapper<Comment> {

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int selectAllCount();
}