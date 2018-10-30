package chapter04.ConcurrencyThead;

/**
 * Create by haifei on 30/10/2018 4:19 PM.
 */
public class DaemonTest {

  public static void main(String[] args) {
    new Thread(new DeamonThread(), "DaemonThread").start();
  }


  static class DeamonThread implements Runnable {

    @Override
    public void run() {
      try {
        SleepUtils.second(10);
      } finally {
        System.out.println("Daemon 线程执行完毕");
      }
    }
  }
}
