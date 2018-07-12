package how.tomcat.work.ex01;

import java.io.IOException;
import java.io.InputStream;

/**
 * Create by haifei on 12/7/2018.
 */
public class Request {

  private InputStream input;
  private String uri;

  public Request(InputStream inputStream) {
    this.input = inputStream;
  }

  public String getUri() {
    return uri;
  }

  public void parse() {
    StringBuffer request = new StringBuffer(2048);
    int i;
    byte[] buffer = new byte[2048];
    try {
      i = input.read(buffer);
    } catch (IOException e) {
      e.printStackTrace();
      i = -1;
    }

    for (int j =0; j < i; j++) {
      request.append((char) buffer[j]);
    }

    //请求对象
    System.out.println(request.toString());
    uri = parseUri(request.toString());
  }

  /**
   * 解析获取请求内容，如：/index.html
   */
  private String parseUri(String requestString) {
    int index1, index2;
    index1 = requestString.indexOf(' ');
    if (index1 != -1) {
      index2 = requestString.indexOf(' ', index1 + 1);
      if (index2 > index1) {
        return requestString.substring(index1 + 1, index2);
      }
    }
    return null;
  }
}
