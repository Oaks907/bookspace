package chapter04.ConcurrencyThead;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Create by haifei on 23/10/2018 1:26 PM.
 * 查看简单应用程序启动时，运行的线程数
 */
public class MultiThread {

  public static void main(String[] args) {
    ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
    ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
    for (int i = 0; i < threadInfos.length; i++) {
      System.out.println("[" + threadInfos[i].getThreadId() + "]" + threadInfos[i].getThreadName());
    }
  }
}
