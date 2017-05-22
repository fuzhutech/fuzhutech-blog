package com.fuzhutech.controller.blog;

import com.fuzhutech.entity.blog.Options;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 设置选项
@RestController
@RequestMapping("/options")
public class OptionsController extends BlogRestfulController<Options> {

  @Override
  protected Integer getModelId(Options model) {
    return model.getId();
  }

}
