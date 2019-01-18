package com.haifei.performance.string;

import org.junit.Test;

import java.util.StringTokenizer;

/**
 * Create by haifei on 17/1/2019 9:27 PM.
 * 测试 String split 方法
 * <p>
 * Split耗费时间：210
 * SplitTokenizer耗费时间：20
 * Split Manual 耗费时间：7
 */
public class SplitStringTest {

  @Test
  public void testSplit() {

    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < 1000; i++) {
      stringBuilder.append(i).append(";");
    }

    String str = stringBuilder.toString();

    long time = System.currentTimeMillis();

    for (int i = 0; i < 1000; i++) {
      str.split(";");
    }

    System.out.println("Split耗费时间：" + (System.currentTimeMillis() - time));
  }

  @Test
  public void testSplit_StringTokenizer() {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < 1000; i++) {
      stringBuilder.append(i).append(";");
    }

    String str = stringBuilder.toString();
    StringTokenizer stringTokenizer = new StringTokenizer(stringBuilder.toString(), ";");

    long time = System.currentTimeMillis();

    for (int i = 0; i < 1000; i++) {
      if (stringTokenizer.hasMoreTokens()) {
        stringTokenizer.nextToken();
      }
      stringTokenizer = new StringTokenizer(str, ";");
    }

    System.out.println("SplitTokenizer耗费时间：" + (System.currentTimeMillis() - time));
  }

  @Test
  public void testSplit_Manual() {

    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < 1000; i++) {
      stringBuilder.append(i).append(";");
    }

    String str = stringBuilder.toString();
    long time = System.currentTimeMillis();

    for (int i = 0; i < 1000; i++) {
      while (true) {
        String splitStr = null;
        int j = str.indexOf(";");
        if (j < 0) {
          break;
        }
        splitStr = str.substring(0, j);
        str = str.substring(j + 1);
      }
    }

    System.out.println("Split Manual 耗费时间：" + (System.currentTimeMillis() - time));
  }
}
