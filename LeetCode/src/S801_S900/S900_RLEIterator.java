package S801_S900;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Create by haifei on 24/12/2019 6:42 PM.
 */
public class S900_RLEIterator {

  public S900_RLEIterator() {

  }

  Queue<Item> list = new LinkedList<>();

  public S900_RLEIterator(int[] A) {
    for (int i = 0; i < A.length; i = i + 2) {
      Item item = new Item(A[i], A[i + 1]);
      list.offer(item);
    }
  }

  public int next(int n) {
    while (n > 0) {

      if (list.isEmpty()) {
        return -1;
      }

      Item peek = list.peek();
      if (peek.times >= n) {
        peek.times -= n;
        return peek.value;
      } else {
        n -= peek.times;
        list.poll();
      }
    }
    return -1;
  }


  public class Item {
    public Integer times;
    public Integer value;

    public Item(Integer times, Integer value) {
      this.times = times;
      this.value = value;
    }
  }

// 放在其他地方跑
//  @Test
//  public void test() {
//    int[] arr = {3, 8, 0, 9, 2, 5};
//
//    S900_RLEIterator rleIterator = new S900_RLEIterator(arr);
//    System.out.println(rleIterator.next(2));
//    System.out.println(rleIterator.next(1));
//    System.out.println(rleIterator.next(1));
//    System.out.println(rleIterator.next(2));
//  }

}
