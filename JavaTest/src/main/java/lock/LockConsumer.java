package lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Create by haifei on 24/12/2018 5:04 PM.
 */
public class LockConsumer {

  private Lock lock = new ReentrantLock();
  Condition notFull = lock.newCondition();
  Condition notEmpty = lock.newCondition();
  volatile int size = 0;

  class producer implements Runnable {

    @Override
    public void run() {
      lock.lock();

      try {
        while (size == 10) {
          try {
            notFull.await();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }

        size++;
        notEmpty.signal();
      } finally {
        lock.unlock();
      }
    }
  }

  class Consumer implements Runnable {

    @Override
    public void run() {

      lock.lock();

      try {

        while (size == 0) {
          try {
            notEmpty.await();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }

        size--;
        notFull.signal();
      } finally {
        lock.unlock();
      }
    }
  }
}
