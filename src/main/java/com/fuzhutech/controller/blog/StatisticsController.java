package com.fuzhutech.controller.blog;

import com.fuzhutech.entity.blog.Options;
import com.fuzhutech.entity.blog.Statistics;
import com.fuzhutech.service.blog.CommentService;
import com.fuzhutech.service.blog.OptionService;
import com.fuzhutech.service.blog.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// 文章评论
@RestController
//@RequestMapping("/login")
public class StatisticsController {

    private static Logger logger = LoggerFactory.getLogger(StatisticsController.class);

    private PostService postService;
    private CommentService commentService;
    private OptionService optionService;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setOptionService(OptionService optionService) {
        this.optionService = optionService;
    }

    //登录
    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    @ResponseBody
    public Statistics getSingle() {
        logger.info("Statistics getSingle");
        Statistics stat = new Statistics();
        stat.setTotalPosts(postService.selectAllCount());
        //TODO:后期修改为表中字段
        stat.setTotalComments(commentService.selectAllCount());

        Options options = new Options();
        options.setName("totalAccess");
        List<Options> list = optionService.queryListByWhere(options);
        if (!list.isEmpty()){
            Options result = list.get(0);
            stat.setTotalAccess(Integer.parseInt(result.getValue()));
        }

        return stat;
    }

}
