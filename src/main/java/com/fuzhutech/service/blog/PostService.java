package com.fuzhutech.service.blog;

import com.fuzhutech.common.service.BaseService;
import com.fuzhutech.pojo.blog.Post;

public interface PostService extends BaseService<Post> {

    int selectAllCount();
}
