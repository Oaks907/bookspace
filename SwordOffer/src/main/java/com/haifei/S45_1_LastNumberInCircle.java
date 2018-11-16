package com.haifei;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Create by haifei on 16/11/2018 12:05 AM.
 * 圆圈中最后的数字
 */
public class S45_1_LastNumberInCircle {

  int lastRemaining(int n, int m) {
    if (n < 1 || m < 1) {
      return -1;
    }

    List<Integer> list = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      list.add(i);
    }

    int index = 0;

    while (list.size() > 1) {

      if (index >= list.size() - 1) {
        index = 0;
      }

      for (int i = 1; i < m; i++) {
        if (index >= list.size() - 1) {
          index = 0;
        } else {
          index++;
        }
      }

      System.out.println(index + " " + list);
      list.remove(index);
    }

    return list.get(0);
  }

  public static void main(String[] args) {
    System.out.println(new S45_1_LastNumberInCircle().lastRemaining(5, 3));
  }
}
