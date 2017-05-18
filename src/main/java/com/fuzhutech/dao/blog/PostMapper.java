package com.fuzhutech.dao.blog;

import com.fuzhutech.common.dao.BaseMapper;
import com.fuzhutech.pojo.blog.Post;

public interface PostMapper extends BaseMapper<Post> {
    int updateByPrimaryKeyWithBLOBs(Post record);
    int updateCount(Post record);
    int selectAllCount();
}