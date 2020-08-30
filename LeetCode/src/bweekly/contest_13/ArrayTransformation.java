package bweekly.contest_13;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Create by haifei on 3/11/2019 12:04 AM.
 */
public class ArrayTransformation {

  public List<Integer> transformArray(int[] arr) {

    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < arr.length; i++) {
      result.add(arr[i]);
    }

    while (true) {
      List<Integer> list = new ArrayList<>(arr.length);
      for (int i = 0; i < result.size(); i++) {

        if (i == 0) {
          list.add(result.get(0));
          continue;
        }

        if (i == result.size() - 1) {
          list.add(result.get(result.size() - 1));
          continue;
        }

        if (result.get(i) > result.get(i - 1) && result.get(i) > result.get(i + 1)) {
          list.add(result.get(i) - 1);
          continue;
        }

        if (result.get(i) < result.get(i - 1) && result.get(i) < result.get(i + 1)) {
          list.add(result.get(i) + 1);
          continue;
        }

        list.add(result.get(i));
      }

      if (result.size() == list.size()) {
        if (isEquals(result, list)) {
          return result;
        } else {
          result = list;
        }
      } else {
        result = list;
      }
    }

  }

  private boolean isEquals(List<Integer> result, List<Integer> list) {
    for (int i = 0; i < result.size(); i++) {
      if (result.get(i) != list.get(i)) {
        return false;
      }
    }
    return true;
  }

  @Test
  public void test() {
    int[] arr = {6, 2, 3, 4};

    List<Integer> list = transformArray(arr);

    // 6,3,3,4
    System.out.println(list);
  }

  @Test
  public void test1() {
    int[] arr = {1, 6, 3, 4, 3, 5};

    List<Integer> list = transformArray(arr);

    // 1,5,4,3,4,5
    // 1,4,4,4,4,5
    // 1,4,4,4,4,5
    System.out.println(list);
  }
}
