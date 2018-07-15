package S401_500;

/**
 * Create by haifei on 28/1/2018.
 */
public class S414_ThirdMaximumNumber {
  public static int thirdMax(int[] nums) {
    Integer max1 = null;
    Integer max2 = null;
    Integer max3 = null;

    for (Integer cur : nums) {
      if (cur.equals(max1) || cur.equals(max2) || cur.equals(max3)) {
        continue;
      }

      if (max1 == null || cur > max1) {
        max3 = max2;
        max2 = max1;
        max1 = cur;
      } else if (max2 == null || cur > max2) {
        max3 = max2;
        max2 = cur;
      } else if (max3 == null || cur > max3) {
        max3 = cur;
      }
    }
    return max3 == null ? max1 : max3;
  }

  public static void main(String[] args) {
    int[] nums = {1, 2};
    System.out.println(thirdMax(nums));
  }
}
