package com.fuzhutech.service.blog.imp;


import com.fuzhutech.common.service.impl.BaseServiceImpl;
import com.fuzhutech.dao.blog.CommentMapper;
import com.fuzhutech.pojo.blog.Comment;
import com.fuzhutech.service.blog.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService {

    @Override
    public int selectAllCount() {
        return ((CommentMapper)this.mapper).selectAllCount();
    }
}
