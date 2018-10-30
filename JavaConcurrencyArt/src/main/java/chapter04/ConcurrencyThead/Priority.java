package chapter04.ConcurrencyThead;

import sun.nio.cs.FastCharsetProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Create by haifei on 23/10/2018 1:31 PM.
 *
 * 设置优先级只是告诉JVM,该线程的优先级比较高，但具体的执行顺序，并不能保证
 *
 */
public class Priority {
  private static volatile boolean notStart = true;
  private static volatile boolean notEnd = true;

  public static void main(String[] args) throws InterruptedException {
    List<Job> jobList = new ArrayList<Job>();
    for (int i = 0; i < 10; i++) {
      int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
      Job job = new Job(priority);
      jobList.add(job);
      Thread thread = new Thread(job, "Thread:" + i);
      thread.setPriority(priority);
      thread.start();
    }

    notStart = false;
    TimeUnit.SECONDS.sleep(10);
    notEnd = false;
    for (Job job : jobList) {
      System.out.println("Job priority:" + job.priority + " ,Job Count:" + job.jobCount);
    }
  }

  static class Job implements Runnable {

    private int priority;
    private long jobCount;

    public Job(int priority) {
      this.priority = priority;
    }

    @Override
    public void run() {
      while (notStart) {
        Thread.yield();
      }
      while (notEnd) {
        Thread.yield();
        jobCount++;
      }
    }
  }
}
