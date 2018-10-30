package chapter04.ConcurrencyThead;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.security.PrivateKey;

/**
 * Create by haifei on 30/10/2018 5:54 PM.
 */
public class Piped {

  public static void main(String[] args) throws IOException {
    PipedWriter out = new PipedWriter();
    PipedReader in = new PipedReader();

    out.connect(in);

    Thread printThread = new Thread(new Print(in), "PrintThread");
    printThread.start();
    int receive = 0;

    try {
      while ((receive = System.in.read()) != -1) {
        out.write(receive);
      }
    } finally {
      out.close();
    }

  }

  static class Print implements Runnable {
    private PipedReader in;

    public Print(PipedReader in) {
      this.in = in;
    }

    @Override
    public void run() {
      int receive = 0;
      try {
        while ((receive = in.read()) != -1) {
          System.out.print((char)receive);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
