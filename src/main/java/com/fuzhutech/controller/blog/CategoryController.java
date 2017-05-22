package com.fuzhutech.controller.blog;

import com.fuzhutech.entity.blog.Category;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 文章分类
@RestController
@RequestMapping("/categorys")
public class CategoryController extends BlogRestfulController<Category> {

  @Override
  protected Integer getModelId(Category model) {
    return model.getId();
  }

}
