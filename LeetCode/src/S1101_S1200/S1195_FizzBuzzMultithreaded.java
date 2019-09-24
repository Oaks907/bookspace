package S1101_S1200;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * Create by haifei on 20/9/2019 12:22 AM.
 */
public class S1195_FizzBuzzMultithreaded {

  private final int n;
  private final Semaphore[] semaphores;
  private final int NUM = 0;
  private final int FIZZ = 1;
  private final int BUZZ = 2;
  private final int FIZZBUZZ = 3;

  public S1195_FizzBuzzMultithreaded(int n) {
    this.n = n;
    semaphores = new Semaphore[4];
    semaphores[NUM] = new Semaphore(1);
    semaphores[FIZZ] = new Semaphore(0);
    semaphores[BUZZ] = new Semaphore(0);
    semaphores[FIZZBUZZ] = new Semaphore(0);
  }

  private synchronized Semaphore getSemaphore(int x) {

    if (x % 3 == 0 && x % 5 == 0) {
      return semaphores[FIZZBUZZ];
    }
    if (x % 3 == 0) {
      return semaphores[FIZZ];
    }
    if (x % 5 == 0) {
      return semaphores[BUZZ];
    }
    return semaphores[NUM];
  }

  // printFizz.run() outputs "fizz".
  public void fizz(Runnable printFizz) throws InterruptedException {

    for (int i = 3; i <= n; i += 3) {

      if(i%5 == 0) continue;
      getSemaphore(i).acquire();
      printFizz.run();
      getSemaphore(i + 1).release();
    }
  }

  // printBuzz.run() outputs "buzz".
  public void buzz(Runnable printBuzz) throws InterruptedException {

    for (int i = 5; i <= n; i += 5) {

      if(i%3 == 0) continue;
      getSemaphore(i).acquire();
      printBuzz.run();
      getSemaphore(i + 1).release();
    }
  }

  // printFizzBuzz.run() outputs "fizzbuzz".
  public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
    for (int i = 15; i <= n; i += 15) {

      getSemaphore(i).acquire();
      printFizzBuzz.run();
      getSemaphore(i + 1).release();
    }
  }

  // printNumber.accept(x) outputs "x", where x is an integer.
  public void number(IntConsumer printNumber) throws InterruptedException {
    for (int i = 1; i <= n; i++) {

      if (i % 3 != 0 && i % 5 != 0) {

        getSemaphore(i).acquire();
        printNumber.accept(i);
        getSemaphore(i + 1).release();
      }
    }
  }
}
