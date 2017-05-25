package com.fuzhutech.service.blog.imp;


import com.fuzhutech.common.service.impl.BaseServiceImpl;
import com.fuzhutech.dao.blog.CommentMapper;
import com.fuzhutech.dao.blog.PostMapper;
import com.fuzhutech.entity.blog.Comment;
import com.fuzhutech.entity.blog.Post;
import com.fuzhutech.service.blog.CommentService;
import com.fuzhutech.service.blog.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService {

    @Autowired
    PostService postService;

    @Override
    public Integer add(Comment record) {
        //设置创建时间
        Date date = new Date();
        record.setCreateTime(date);
        int result = this.mapper.insert(record);

        //增加文章的评论量
        Post post = new Post();
        post.setId(record.getPostId());
        post.setCommentCount(1);
        postService.updateIncreaseCount(post);

        return result;
    }

    @Override
    public int selectAllCount() {
        return ((CommentMapper)this.mapper).selectAllCount();
    }
}
