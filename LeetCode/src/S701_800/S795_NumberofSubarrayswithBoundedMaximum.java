package S701_800;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by haifei on 15/1/2020 10:16 PM.
 */
public class S795_NumberofSubarrayswithBoundedMaximum {


  public int numSubarrayBoundedMax2(int[] A, int L, int R) {

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> list = new ArrayList<>();


    for (int i = 0; i < A.length; i++) {

      int maxVal = A[i];
      list.add(A[i]);
      if (maxVal >= L && maxVal <= R) {
        result.add(new ArrayList<>(list));
      } else if (maxVal > R) {
        list.clear();
        continue;
      }

      for (int j = i + 1; j < A.length; j++) {
        maxVal = Math.max(maxVal, A[j]);
        list.add(A[j]);
        if (maxVal >= L && maxVal <= R) {
          result.add(new ArrayList<>(list));
        } else if (maxVal > R) {
          break;
        }
      }
      list.clear();
    }

//    System.out.println(result);

    return result.size();
  }

  public int numSubarrayBoundedMax(int[] A, int L, int R) {
    int res = 0;
    int count = 0;
    int j = 0;
    for (int i = 0; i < A.length; i++) {
      if (A[i] >= L && A[i] <= R) {
        res += i - j + 1;
        count = i - j + 1;
      } else if (A[i] < L) {
        res += count;
      } else {
        j = i + 1;
        count = 0;
      }
    }

    return res;
  }

  @Test
  public void test1() {
    int[] arr = {73, 55, 36, 5, 55, 14, 9, 7, 72, 52};

    int result = numSubarrayBoundedMax2(arr, 32, 69);

    Assert.assertEquals(22, result);
  }

  @Test
  public void test2() {
    int[] arr = {876, 880, 482, 260, 132, 421, 732, 703, 795, 420, 871, 445, 400, 291, 358, 589,
      617, 202, 755, 810, 227, 813, 549, 791, 418, 528, 835, 401, 526, 584, 873, 662, 13, 314,
      988, 101, 299, 816, 833, 224, 160, 852, 179, 769, 646, 558, 661, 808, 651, 982, 878, 918,
      406, 551, 467, 87, 139, 387, 16, 531, 307, 389, 939, 551, 613, 36, 528, 460, 404, 314, 66,
      111, 458, 531, 944, 461, 951, 419, 82, 896, 467, 353, 704, 905, 705, 760, 61, 422, 395, 298
      , 127, 516, 153, 299, 801, 341, 668, 598, 98, 241};

    int result = numSubarrayBoundedMax2(arr, 658, 719);

    Assert.assertEquals(19, result);
  }


  @Test
  public void test() {
    int[] arr = {2, 1, 4, 3};

    int result = numSubarrayBoundedMax(arr, 2, 3);

    Assert.assertEquals(3, result);
  }
}
