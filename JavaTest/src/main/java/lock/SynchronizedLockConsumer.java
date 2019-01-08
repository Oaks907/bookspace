package lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Create by haifei on 24/12/2018 4:20 PM.
 */
public class SynchronizedLockConsumer {

  private static Integer count = 0;
  private static final Integer FULL = 10;

  private static final String LOCK = "lock";

  public class producer implements Runnable {

    @Override
    public void run() {
      for (int i = 0; i < 10; i++) {
        try {
          Thread.sleep(30000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        synchronized (LOCK) {
          while (count == FULL) {
            try {
              LOCK.wait();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          count++;

          LOCK.notifyAll();
        }
      }
    }
  }

  public class Consumer implements Runnable{

    @Override
    public void run() {
      for (int i = 0; i < 10; i++) {

        synchronized (LOCK) {
          while (count == 0) {
            try {
              LOCK.wait();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
        count--;
        System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有" + count);
        LOCK.notifyAll();
      }
    }
  }


}
