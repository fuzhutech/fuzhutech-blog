package com.fuzhutech.controller.blog;

import com.fuzhutech.common.ResponseResult;
import com.fuzhutech.entity.blog.Post;
import com.fuzhutech.service.blog.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 文章
@RestController
@RequestMapping("/posts")
public class PostController extends BlogRestfulController<Post> {

    private static Logger logger = LoggerFactory.getLogger(PostController.class);

    @RequestMapping(value = "updateIncreaseCount", method = RequestMethod.PUT)
    public ResponseResult edit(HttpServletRequest request, HttpServletResponse response, @RequestBody Post model) {

        try {
            ((PostService)service).updateIncreaseCount(model);
            return new ResponseResult(ResponseResult.SUCCESS);
        } catch (RuntimeException ex) {
            logger.error("更新增加阅读量失败：{}", ex);
            return new ResponseResult(ResponseResult.FAILURE, null, ex.getMessage());
        }
    }
}
