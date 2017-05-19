package com.fuzhutech.controller.blog;

import com.fuzhutech.pojo.blog.Upload;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 上传
@RestController
@RequestMapping("/uploads")
public class UploadController extends BlogRestfulController<Upload> {

  @Override
  protected Integer getModelId(Upload model) {
    return model.getId();
  }

}
