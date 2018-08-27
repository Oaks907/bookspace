package com.wisely.highlight_springmvc4.messageconverter;

import com.wisely.highlight_springmvc4.Domain.DemoObj;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Create by haifei on 26/8/2018.
 */
public class MyMessageConverter extends AbstractHttpMessageConverter<DemoObj> {

  public MyMessageConverter() {
    super(new MediaType("application", "x-wisely", Charset.forName("UTF-8")));
  }

  @Override
  protected boolean supports(Class<?> aClass) {
    return DemoObj.class.isAssignableFrom(aClass);
  }

  //处理Request中的数据
  @Override
  protected DemoObj readInternal(Class<? extends DemoObj> aClass, HttpInputMessage httpInputMessage) throws
    IOException, HttpMessageNotReadableException {

    String temp = StreamUtils.copyToString(httpInputMessage.getBody(), Charset.forName("UTF-8"));
    String[] tempArr = temp.split("-");

    return new DemoObj(new Long(tempArr[0]), tempArr[1]);
  }

  //处理写入到Response中的数据
  @Override
  protected void writeInternal(DemoObj obj, HttpOutputMessage httpOutputMessage) throws IOException,
    HttpMessageNotWritableException {
    String out = "hello:" + obj.getId() + "-" + obj.getName();
    httpOutputMessage.getBody().write(out.getBytes());
  }
}
