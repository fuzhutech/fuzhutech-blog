package com.fuzhutech.service.blog.imp;

import com.fuzhutech.common.service.impl.BaseServiceImpl;
import com.fuzhutech.dao.blog.PostMapper;
import com.fuzhutech.entity.blog.Post;
import com.fuzhutech.service.blog.PostService;
import com.fuzhutech.util.blog.JsoupUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PostServiceImpl extends BaseServiceImpl<Post> implements PostService {

    private static int excerptLength = 150;

    @Override
    public Integer add(Post record) {
        //设置摘要
        setExcerpt(record);
        //设置创建日期、更新日期
        Date date = new Date();
        record.setCreateTime(date);
        record.setLastModifyTime(date);

        return this.mapper.insert(record);
    }

    @Override
    public Integer update(Post record) {
        //设置摘要
        setExcerpt(record);
        //设置更新日期
        record.setLastModifyTime(new Date());
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateSelective(Post record) {
        if(!record.getContent().isEmpty())
            setExcerpt(record);

        //设置更新日期
        record.setLastModifyTime(new Date());

        return this.mapper.updateByPrimaryKeySelective(record);
    }

    //设置摘要
    private void setExcerpt(Post record){
        String content = record.getContent();
        String cleanTxt = JsoupUtils.plainText(content);
        record.setExcerpt(cleanTxt.length() > excerptLength ? cleanTxt.substring(0, excerptLength) : cleanTxt);
    }

    @Override
    public int selectAllCount() {
        return ((PostMapper)this.mapper).selectAllCount();
    }

    //更新增加点击量、评论量，不更新修改时间
    public Integer updateIncreaseCount(Post record) {
        return ((PostMapper)this.mapper).updateIncreaseCount(record);
    }

}
