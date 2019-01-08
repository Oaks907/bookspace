package lock;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Create by haifei on 24/12/2018 5:14 PM.
 */
public class BlockingQueueConsumer {

  private ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(10);

  class Producer implements Runnable {

    @Override
    public void run() {
      try {
        blockingQueue.put(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  class Consumer implements Runnable {

    @Override
    public void run() {
      try {
        blockingQueue.take();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
