package com.fuzhutech.service.blog.imp;

import com.fuzhutech.common.service.impl.BaseServiceImpl;
import com.fuzhutech.dao.blog.PostMapper;
import com.fuzhutech.pojo.blog.Post;
import com.fuzhutech.service.blog.PostService;
import com.fuzhutech.util.blog.JsoupUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl extends BaseServiceImpl<Post> implements PostService {

    private static Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    @Override
    public Integer add(Post record) {
        setExcerpt(record);
        return this.mapper.insert(record);
    }

    @Override
    public Integer update(Post record) {
        logger.info(record.getContent());
        setExcerpt(record);
        return this.mapper.updateByPrimaryKeySelective(record);
        //return this.mapper.updateByPrimaryKey(record);
    }

    @Override
    public Integer updateSelective(Post record) {
        if(!record.getContent().isEmpty())
            setExcerpt(record);

        return this.mapper.updateByPrimaryKeySelective(record);
    }

    private void setExcerpt(Post record){
        String content = record.getContent();
        //record.setContent(JsoupUtils.filter(content));
        String cleanTxt = JsoupUtils.plainText(content);
        int excerpt_length = 150;
        record.setExcerpt(cleanTxt.length() > excerpt_length ? cleanTxt.substring(0, excerpt_length) : cleanTxt);
    }

    @Override
    public int selectAllCount() {
        return ((PostMapper)this.mapper).selectAllCount();
    }
}
