package S101_200;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Create by haifei on 15/10/2019 8:56 AM.
 */
public class S136_SingleNumber {

  public int singleNumber(int[] nums) {

    int i = nums[0];

    for (int j = 1; j < nums.length; j++) {
      i = i ^ nums[j];
    }

    return i;
  }

  @Test
  public void test() {

    int[] arr =  {2, 2, 1};

    int result = singleNumber(arr);

    Assert.assertEquals(1, result);
  }
}
