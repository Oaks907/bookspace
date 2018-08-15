package how.tomcat.work.ex14.startup;

import java.io.OutputStream;
import java.net.Socket;

/**
 * Create by haifei on 16/8/2018.
 */
public class Stopper {

  public static void main(String[] args) {
    int port = 8005;

    try {
      Socket socket = new Socket("127.0.0.1", port);
      OutputStream outputStream = socket.getOutputStream();
      String shutdown = "SHUTDOWN";
      for (int i = 0; i < shutdown.length(); i++) {
        outputStream.write(shutdown.charAt(i));
      }

      outputStream.flush();
      outputStream.close();
      outputStream.close();
      System.out.println("The server was successfully shutdown.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
