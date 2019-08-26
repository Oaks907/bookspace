package S1001_S1100;

import org.junit.Before;
import org.junit.Test;
import utils.ListNode;
import utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by haifei on 4/4/2019 4:45 PM.
 * https://leetcode.com/problems/next-greater-node-in-linked-list/
 */
public class S1019_NextGreaterNodeInLinkedList {

  public int[] nextLargerNodes(ListNode head) {

    if (head == null) {
      return null;
    }

    List<Integer> list = new ArrayList<>();

    while (head != null) {
      list.add(head.val);
      head = head.next;
    }

    int size = list.size();
    int curVal;
    int nextVal = list.get(size - 1);
    int[] result = new int[size];
    result[size - 1] = 0;


    for (int i = size - 2; i >= 0; i--) {
      curVal = list.get(i);


      if (curVal < nextVal) { // 当前元素小于下一个元素
        result[i] = nextVal;
      } else if (curVal < result[i + 1]) {
        result[i] = result[i + 1];
      } else {
        result[i] = 0;
      }

      nextVal = curVal;

    }

    return result;
  }

  public int[] helper(int[] array) {

    int curVal;
    int nextVal = array[array.length - 1];
    int[] result = new int[array.length];
    result[array.length - 1] = 0;


    for (int i = array.length - 2; i >= 0; i--) {
      curVal = array[i];

      if (curVal < nextVal) { // 当前元素小于下一个元素
        result[i] = nextVal;
      } else { // 当前元素大于下一个元素

        if (curVal < result[i + 1]) {
          result[i] = result[i + 1];
        } else {

//          result[i] == 0;

          for (int j = i + 1; j < array.length; j++) {

          }
        }
      }

      nextVal = curVal;
    }

    return result;
  }

  private S1019_NextGreaterNodeInLinkedList instance;

  @Before
  public void before() {
    instance = new S1019_NextGreaterNodeInLinkedList();
  }

  @Test
  public void test2() {
    int[] A = {9, 7, 6, 7, 6, 9};

    PrintUtils.printArray(instance.helper(A)); //[0,9,7,9,9,0]
  }

  @Test
  public void test1() {
    int[] A = {1, 7, 5, 1, 9, 2, 5, 1};

    PrintUtils.printArray(instance.helper(A)); //7,9,9,9,0,5,0,0
  }
}
