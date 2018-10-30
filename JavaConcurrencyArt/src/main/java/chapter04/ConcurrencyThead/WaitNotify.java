package chapter04.ConcurrencyThead;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Create by haifei on 30/10/2018 5:13 PM.
 */
public class WaitNotify {
  private static boolean flag = true;
  private static Object lock = new Object();

  public static void main(String[] args) throws InterruptedException {
    Thread waitThread = new Thread(new Wait(), "WaitThread");
    waitThread.start();
    TimeUnit.SECONDS.sleep(1);
    Thread notifyThread = new Thread(new Notify(), "NotifyThread");
    notifyThread.start();
  }

  private static class Wait implements Runnable {

    @Override
    public void run() {
      synchronized (lock) {
        while (flag) {
          System.out.println(Thread.currentThread() + " flag is true. wait @ "
            + new SimpleDateFormat("HH:mm:ss").format(new Date()));
          try {
            lock.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
      System.out.println(Thread.currentThread() + " flag is false. running @ "
        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }
  }

  private static class Notify implements Runnable {

    @Override
    public void run() {
      synchronized (lock) {
        System.out.println(Thread.currentThread() + " hold lock. notify @ "
          + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        lock.notify();
        flag = false;
        SleepUtils.second(5);
      }

      synchronized (lock) {
        System.out.println(Thread.currentThread() + " hold lock. notify @ "
          + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        SleepUtils.second(5);
      }
    }
  }
}
