package chapter04.ConcurrencyThead;

import java.util.concurrent.TimeUnit;

/**
 * Create by haifei on 30/10/2018 4:25 PM.
 * 中断
 */
public class Interrupted {

  public static void main(String[] args) throws InterruptedException {
    //SleepThead  不停的尝试睡眠
    Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
    sleepThread.setDaemon(true);
    //BusyThread  不停的运行
    Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
    busyThread.setDaemon(true);
    sleepThread.start();
    busyThread.start();

    //休眠五秒，让线程充分运行
    TimeUnit.SECONDS.sleep(5);

    sleepThread.interrupt();
    busyThread.interrupt();
    System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
    System.out.println("BusyThread interrupted is " + sleepThread.isInterrupted());
    SleepUtils.second(2);
  }

  static class SleepRunner implements Runnable {

    @Override
    public void run() {
      while (true) {
        try {
          TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  static class BusyRunner implements Runnable {

    @Override
    public void run() {
      while (true) {

      }
    }
  }
}
