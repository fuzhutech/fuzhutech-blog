package com.fuzhutech.controller.blog;

import com.fuzhutech.pojo.blog.Comment;
import com.fuzhutech.pojo.blog.Post;
import com.fuzhutech.service.blog.CommentService;
import com.fuzhutech.service.blog.PostService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//返回jsp视图
@Controller
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    //private String host = "http://www.fuzhutech.com";
    private String host = "http://localhost:8080";

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {

        List<Post> postList = postService.queryListByWhere(null);
        model.addAttribute("postList", postList);
        model.addAttribute("g_domain", host);
        model.addAttribute("g_title", "FuzhuTech");

        return "index";
    }

    @RequestMapping(value = "/sitemap.xml", method = RequestMethod.GET)
    public void sitemap(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Post> postList = postService.queryListByWhere(null);
        createDocument(postList, request);

        request.getRequestDispatcher("/sitemap.xml").forward(request, response);
    }

    private void createDocument(List<Post> postList, HttpServletRequest request) throws IOException {

        // XML 声明 <?xml version="1.0" encoding="UTF-8"?> 自动添加到 XML文档中
        //创建一篇文档
        Document document = DocumentHelper.createDocument();
        //添加一个元素
        Element urlSetEl = document.addElement("urlset", "http://www.sitemaps.org/schemas/sitemap/0.9");
        urlSetEl.addNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        urlSetEl.addAttribute("xsi:schemaLocation", "http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        //添加首页
        Element urlEl = urlSetEl.addElement("url", "http://www.sitemaps.org/schemas/sitemap/0.9");
        urlEl.addElement("loc").setText(host);
        urlEl.addElement("lastmod").addText(simpleDateFormat.format(new Date()));
        urlEl.addElement("changefreq").addText("daily");
        urlEl.addElement("priority").addText("1.0");

        //添加文章列表
        Post post;
        for (Post aPostList : postList) {
            post = aPostList;

            //创建元素
            urlEl = urlSetEl.addElement("url", "http://www.sitemaps.org/schemas/sitemap/0.9");
            urlEl.addElement("loc").setText(host + "/p/" + post.getId());
            urlEl.addElement("lastmod").addText(simpleDateFormat.format(post.getLastModifyTime()));
            urlEl.addElement("changefreq").addText("weekly");
            urlEl.addElement("priority").addText("0.8");
        }

        //把生成的xml文档存放在硬盘上  true代表是否换行
        OutputFormat format = new OutputFormat("    ", true);
        //把生成的xml文档存放在硬盘上  true代表是否换行
        format.setEncoding("UTF-8");//设置编码格式
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(rootPath + "sitemap.xml"), format);
        xmlWriter.write(document);
        xmlWriter.close();
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public String postPage(@PathVariable("id") Integer id, Model model) {
        logger.info("post controller");

        Post post = postService.queryById(id);
        Comment record = new Comment();
        record.setPostId(id);
        List<Comment> commentList = commentService.queryListByWhere(record);


        model.addAttribute("post", post);
        model.addAttribute("commentList", commentList);
        model.addAttribute("g_domain", host);
        model.addAttribute("g_title", post.getTitle());

        return "post";
    }

}
