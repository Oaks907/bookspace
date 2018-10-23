package chapter01.ConcurrencyChange;

/**
 * Create by haifei on 22/10/2018 10:30 AM.
 * 结论，在百万数据之前串行执行的速度更快,
 * 百万数据之后并发执行速度更快，大约快两倍（1亿与1千万数据）
 */
public class ConcurrencyTest {

  public static void main(String[] args) throws InterruptedException {
    long count = 100000000L;
    System.out.println("1亿：");
    concurrency(count);
    serail(count);
    System.out.println("1千万：");
    count = 10000000L;
    concurrency(count);
    serail(count);
    System.out.println("1百万：");
    count = 1000000L;
    concurrency(count);
    serail(count);
    System.out.println("1万：");
    count = 100000L;
    concurrency(count);
    serail(count);
  }

  /**
   * 并发处理
   * @param count 处理次数
   */
  private static void concurrency(final long count) throws InterruptedException {
    long startTime = System.currentTimeMillis();

    Thread thread = new Thread(new Runnable() {
      public void run() {
        int a = 5;
        for (int i = 0; i < count; i++) {
          a += 5;
        }
      }
    });

    thread.start();
    int b = 0;
    for (long i = 0; i < count; i++) {
      b--;
    }
    thread.join();
    long time = System.currentTimeMillis() - startTime;
    System.out.println("concurrency:" + time + "ms, b=" + b);
  }

  private static void serail(final long count) {
    long startTime = System.currentTimeMillis();
    int a = 5;
    for (int i = 0; i < count; i++) {
      a += 5;
    }
    int b = 0;
    for (long i = 0; i < count; i++) {
      b--;
    }
    long time = System.currentTimeMillis() - startTime;
    System.out.println("concurrency:" + time + "ms, b=" + b);
  }
}
