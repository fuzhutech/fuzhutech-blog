package com.fuzhutech.dao.blog;

import com.fuzhutech.common.dao.BaseMapper;
import com.fuzhutech.entity.blog.Post;

public interface PostMapper extends BaseMapper<Post> {
    int updateByPrimaryKeyWithBLOBs(Post record);
    int updateIncreaseCount(Post record);
    int selectAllCount();
}