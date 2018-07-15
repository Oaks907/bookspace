package how.tomcat.work.ex03.connector.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Create by haifei on 14/7/2018.
 */
public class HttpConnector implements Runnable {

  boolean stopped;
  private String schema = "http";

  public String getSchema() {
    return this.schema;
  }


  public void run() {
    ServerSocket serverSocket = null;
    int port = 8081;

    try {
      serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }

    while (!stopped) {
      Socket socket = null;

      try {
        socket = serverSocket.accept();
      } catch (IOException e) {
        e.printStackTrace();
        continue;
      }

      HttpProcessor processor = new HttpProcessor(this);
      processor.process(socket);
    }
  }

  public void start() {
    Thread thread = new Thread(this);
    thread.start();
  }
}
