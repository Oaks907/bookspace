package chapter04.ConcurrencyThead;

import java.util.concurrent.TimeUnit;

/**
 * Create by haifei on 30/10/2018 6:01 PM.
 * Thread Join测试类
 */
public class Join {

  public static void main(String[] args) throws InterruptedException {
    Thread previous = Thread.currentThread();

    for (int i = 0; i < 10; i++) {
      Thread thread = new Thread(new Domino(previous), String.valueOf(i));
      thread.start();
      previous = thread;
    }

    TimeUnit.SECONDS.sleep(5);
    System.out.println(Thread.currentThread().getName() + "is Terminate.");

  }

  static class Domino implements Runnable {

    private Thread thread;

    public Domino(Thread thread) {
      this.thread = thread;
    }

    @Override
    public void run() {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      System.out.println(Thread.currentThread().getName() + " is Terminate.");
    }
  }
}
