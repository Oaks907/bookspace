package S401_500;

/**
 * Create by haifei on 28/1/2018.
 */
public class S485_MaxConsecutiveOnes {
  public int findMaxConsecutiveOnes(int[] nums) {
    int result = 0;
    int temp = 0;

    for (int i : nums) {
      if (i == 1) {
        temp++;
        result = Math.max(result, temp);
      } else {
        temp = 0;
      }
    }
    return result;
  }
}
