package com.fuzhutech.dao.blog;

import com.fuzhutech.common.dao.BaseMapper;
import com.fuzhutech.pojo.blog.Comment;

public interface CommentMapper extends BaseMapper<Comment> {

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int selectAllCount();
}