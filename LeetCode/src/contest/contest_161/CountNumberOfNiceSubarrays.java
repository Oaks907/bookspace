package contest.contest_161;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 3/11/2019 10:45 AM.
 */
public class CountNumberOfNiceSubarrays {

  public int numberOfSubarrays(int[] nums, int k) {

    int cur = 0, ans = 0;
    int[] visited = new int[nums.length + 1];
    visited[0] = 1;

    for (int i = 0; i < nums.length; i++) {
      cur += nums[i] % 2 == 1 ? 1 : 0;
      visited[cur] += 1;
      ans += cur >= k ? visited[cur - k] : 0;
    }

    return ans;
  }

  @Test
  public void test1() {

    int[] arr = {1, 1, 2, 1, 1};

    int result = numberOfSubarrays(arr, 3);

    Assert.assertEquals(2, result);
  }

  @Test
  public void test2() {

    int[] arr = {2, 4, 6};

    int result = numberOfSubarrays(arr, 1);

    Assert.assertEquals(0, result);
  }

  @Test
  public void test3() {

    int[] arr = {2, 2, 2, 1, 2, 2, 1, 2, 2, 2};

    int result = numberOfSubarrays(arr, 2);

    Assert.assertEquals(16, result);
  }

  @Test
  public void test4() {

    int[] arr = {1, 1, 1, 1, 1};

    int result = numberOfSubarrays(arr, 3);

    Assert.assertEquals(5, result);
  }
}
