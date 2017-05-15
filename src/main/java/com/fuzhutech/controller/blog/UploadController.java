package com.fuzhutech.controller.blog;

import com.fuzhutech.pojo.blog.Upload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 上传
@RestController
@RequestMapping("/uploads")
public class UploadController extends BlogRestfulController<Upload> {

  private static Logger logger = LoggerFactory.getLogger(UploadController.class);

  @Override
  protected Integer getModelId(Upload model) {
    return model.getId();
  }

}
