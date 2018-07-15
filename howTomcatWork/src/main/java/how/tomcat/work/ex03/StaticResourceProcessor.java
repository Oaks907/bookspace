package how.tomcat.work.ex03;

import how.tomcat.work.ex03.connector.http.HttpRequest;
import how.tomcat.work.ex03.connector.http.HttpResponse;

import java.io.IOException;

public class StaticResourceProcessor {

  public void process(HttpRequest request, HttpResponse response) {
    try {
      response.sendStaticResource();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

}
