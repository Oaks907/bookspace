package S901_S1000;

import org.junit.Assert;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Create by haifei on 11/12/2019 12:36 PM.
 */
public class S907_SumOfSubarrayMinimums {

  // spece limit will cause wrong
  public int sumSubarrayMins(int[] A) {

    int result = 0;

    for (int i = 0; i < A.length; i++) {
      for (int j = i; j < A.length; j++) {
        result += print(A, i, j);
      }
    }

    return result;
  }

  public int print(int[] A, int start, int end) {

    int min = Integer.MAX_VALUE;

    for (int i = start; i <= end; i++) {
      min = Math.min(min, A[i]);
    }

    return min;
  }


  public int sumSubarrayMins2(int[] A) {
    // init prev less & next less array
    int[] prevLess = new int[A.length];
    int[] nextLess = new int[A.length];
    for (int i = 0; i < A.length; i++) {
      prevLess[i] = A.length;
      nextLess[i] = -1;
    }
    Deque<int[]> stack = new LinkedList<>();
    // calc prev less
    for (int i = 0; i < A.length; i++) {
      while (!stack.isEmpty() && stack.peek()[0] > A[i]) stack.pop();
      prevLess[i] = stack.isEmpty() ? -1 : stack.peek()[1];
      stack.push(new int[]{A[i], i});
    }
    // calc next less
    stack.clear();
    for (int i = A.length - 1; i >= 0; i--) {
      while (!stack.isEmpty() && stack.peek()[0] >= A[i]) stack.pop();
      nextLess[i] = stack.isEmpty() ? A.length : stack.peek()[1];
      stack.push(new int[]{A[i], i});
    }
    // calc result
    int res = 0;
    int mod = (int) 1e9 + 7;
    for (int i = 0; i < A.length; i++) {
      res = (res + A[i] * (i - prevLess[i]) * (nextLess[i] - i)) % mod;
    }
    return res;
  }

  public int sumSubarrayMins3(int[] A) {
    int res = 0, n = A.length, mod = (int) 1e9 + 7;
    int[] left = new int[n], right = new int[n];
    Stack<int[]> s1 = new Stack<>(), s2 = new Stack<>();

    for (int i = 0; i < A.length; i++) {
      int count = 1;

      while (!s1.isEmpty() && s1.peek()[0] > A[i]) {
        count += s1.pop()[1];
      }

      s1.push(new int[]{A[i], count});
      left[i] = count;
    }

    for (int i = A.length - 1; i >= 0; i--) {
      int count = 1;

      while (!s2.isEmpty() && s2.peek()[0] < A[i]) {
        count += s2.pop()[1];
      }

      s2.push(new int[]{A[i], count});
      right[i] = count;
    }

    for (int i = 0; i < A.length; i++) {
      res = (res + A[i] * left[i] * right[i]) % mod;
    }

    return res;
  }


  @Test
  public void test() {
    int[] A = {3, 1, 2, 4};

    int result = sumSubarrayMins2(A);

    Assert.assertEquals(17, result);

    result = sumSubarrayMins3(A);

    Assert.assertEquals(17, result);
  }

  @Test
  public void test1() {
    int[] A = {59, 91};

    int result = sumSubarrayMins(A);

    Assert.assertEquals(209, result);
  }
}
