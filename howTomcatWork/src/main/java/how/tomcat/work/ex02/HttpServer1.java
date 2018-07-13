package how.tomcat.work.ex02;

import javax.servlet.ServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Create by haifei on 12/7/2018.
 */
public class HttpServer1 {
  public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";
  private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
  private boolean shutdown = false;

  public static void main(String[] args) {
    HttpServer1 httpServer = new HttpServer1();
    httpServer.await();
  }

  public void await() {
    ServerSocket serverSocket = null;
    int port = 8080;

    try {
      serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }

    while (!shutdown) {
      Socket socket = null;
      InputStream inputStream = null;
      OutputStream outputStream = null;

      try {
        socket = serverSocket.accept();
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
        Request request = new Request(inputStream);
        request.parse();

        Response response = new Response(outputStream);
        response.setRequest(request);

        if (request.getUri().startsWith("/servlet/")) {
          ServletProcessor1 processor1 = new ServletProcessor1();
          processor1.process(request, response);
        } else {
          StaticResourceProcessor processor = new StaticResourceProcessor();
          processor.process(request, response);
        }

        socket.close();

        shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
      } catch (Exception e) {
        e.printStackTrace();
        continue;
      }
    }
  }
}
