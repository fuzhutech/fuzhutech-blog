package com.fuzhutech.service.blog.imp;


import com.fuzhutech.common.service.impl.BaseServiceImpl;
import com.fuzhutech.dao.blog.CommentMapper;
import com.fuzhutech.pojo.blog.Comment;
import com.fuzhutech.service.blog.CommentService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService {

    @Override
    public Integer add(Comment record) {
        //设置创建时间
        Date date = new Date();
        record.setCreateTime(date);
        return this.mapper.insert(record);
    }

    @Override
    public int selectAllCount() {
        return ((CommentMapper)this.mapper).selectAllCount();
    }
}
