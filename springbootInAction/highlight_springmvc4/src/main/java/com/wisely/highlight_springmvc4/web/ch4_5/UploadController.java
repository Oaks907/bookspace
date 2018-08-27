package com.wisely.highlight_springmvc4.web.ch4_5;

import ch.qos.logback.core.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Create by haifei on 26/8/2018.
 */
@Controller
public class UploadController {


  @RequestMapping(value = "/upload", method = RequestMethod.POST)
  @ResponseBody
  public String upload(MultipartFile file) {

    try {
      FileUtils.writeByteArrayToFile(new File
        ("/Users/haifei/studyspace/bookstudy/springbootInAction/highlight_springmvc4/src/main/resources" + file
          .getOriginalFilename()), file.getBytes());
      return "ok";
    } catch (IOException e) {
      e.printStackTrace();
      return "wrong";
    }
  }
}
