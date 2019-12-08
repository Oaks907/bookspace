package contest.contest_166;

import org.junit.Test;

import java.util.Arrays;

/**
 * Create by haifei on 8/12/2019 11:13 AM.
 */
public class FindtheSmallestDivisorGivenaThreshold {

//  public int smallestDivisor(int[] nums, int threshold) {
//
//    Arrays.sort(nums);
//
//    if (threshold > nums.length) {
//      return 0;
//    }
//
//    int lastVal = nums[nums.length - 1];
//    int count = 1;
//    int temp = lastVal / count;
//    int preTemp = temp;
//
//    int sum = 0;
//
//    while (sum < threshold) {
//      sum = 0;
//
//      for (int i = nums.length - 1; i >= 0; i--) {
//        if (nums[i] < temp) {
//          sum += i + 1;
//          break;
//        } else {
//          sum += getNum(nums[i], temp);
//        }
//      }
//      if (sum == threshold) {
//        return temp;
//      } else if (sum > threshold) {
//        return preTemp;
//      }
//
////      System.out.println("temp:" + temp + " sum:" + sum);
//      preTemp = temp;
//      temp = getNum(lastVal, ++count);
//    }
//
//    return preTemp;
//  }
//
//  public int getNum(int n, int key) {
//    return n % key == 0 ? n / key : n / key + 1;
//  }

  public int smallestDivisor2(int[] A, int threshold) {
    int left = 1, right = (int) 1e6;
    while (left < right) {
      int m = (left + right) / 2;
      int sum = 0;

      for (int i : A) {
        sum += (i + m - 1) / m;
      }

      if (sum > threshold) {
        left = m + 1;
      } else {
        right = m;
      }
    }

    return left;
  }

  @Test
  public void test() {
    int[] nums = {1, 2, 5, 9};

    int result = smallestDivisor2(nums, 6);

    System.out.println(result);
  }

  @Test
  public void test1() {
    int[] nums = {19};

    int result = smallestDivisor2(nums, 5);

    System.out.println(result);
  }

  @Test
  public void test2() {
    int[] nums = {2, 3, 5, 7, 11};

    int result = smallestDivisor2(nums, 11);

    System.out.println(result);
  }

  @Test
  public void test4() {
    int[] nums = {2, 3, 5};

    int result = smallestDivisor2(nums, 1000);

    System.out.println(result);
  }

}
