package chapter04.ConcurrencyThead;

import java.util.concurrent.TimeUnit;

/**
 * Create by haifei on 30/10/2018 4:20 PM.
 */
public class SleepUtils {

  public static final void second(long seconds) {
    try {
      TimeUnit.SECONDS.sleep(seconds);
    } catch (InterruptedException e) {
      System.out.println("Sleep Error.");
    }
  }
}
