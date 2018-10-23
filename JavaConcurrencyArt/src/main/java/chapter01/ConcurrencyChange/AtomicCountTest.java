package chapter01.ConcurrencyChange;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Create by haifei on 22/10/2018 3:33 PM.
 * 测试原子操作
 */
public class AtomicCountTest {
  private AtomicInteger atomic = new AtomicInteger(0);
  private int i = 0;

  public static void main(String[] args) throws InterruptedException {
    final AtomicCountTest cas = new AtomicCountTest();
    List<Thread> threadList = new ArrayList<Thread>();
    long start = System.currentTimeMillis();

    for (int i = 0; i < 100; i++) {
      threadList.add(new Thread(new Runnable() {
        public void run() {
          for (int i = 0; i < 10000; i++) {
            cas.safeCount();
            cas.count();
          }
        }
      }));
    }

    for (Thread t : threadList) {
      t.start();
    }

    for (Thread t : threadList) {
      t.join();
    }

    System.out.println("Atomic:" + cas.atomic.get());
    System.out.println("i:" + cas.i);
    System.out.println("time:" + (System.currentTimeMillis() - start) + "ms");
  }

  private void safeCount() {
    for (;;) {
      int i = atomic.get();
      boolean suc = atomic.compareAndSet(i, ++i);
      if (suc) {
        break;
      }
    }
  }

  private void count() {
    i++;
  }
}

