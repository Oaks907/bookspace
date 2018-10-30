package chapter04.ConcurrencyThead;


/**
 * Create by haifei on 30/10/2018 3:59 PM.
 */
public class ThreadState {

  public static void main(String[] args) {
    new Thread(new TimeWaiting(), "TimeWaitingThread").start();
    new Thread(new Waiting(), "WaitingThead").start();
    //一个获取锁成功，一个被阻塞
    new Thread(new Blocked(), "blockedThread-1").start();
    new Thread(new Blocked(), "blockedThread-2").start();
  }

  //不停的睡眠
  static class TimeWaiting implements Runnable {
    @Override
    public void run() {
      while (true) {
        SleepUtils.second(100);
      }
    }
  }

  static class Waiting implements Runnable {
    @Override
    public void run() {
      while (true) {
        synchronized (Waiting.class) {
          try {
            Waiting.class.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }

  static class Blocked implements Runnable {

    @Override
    public void run() {
      synchronized (Blocked.class) {
        while (true) {
          SleepUtils.second(100);
        }
      }
    }
  }
}
