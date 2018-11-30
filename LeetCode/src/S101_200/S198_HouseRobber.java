package S101_200;

/**
 * Create by haifei on 30/11/2018 8:55 AM.
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 */
public class S198_HouseRobber {

  public int rob(int[] nums) {
    if (nums == null || nums.length == 0) return 0;
    if (nums.length == 1) return nums[0];

    int[] num = new int[nums.length];
    num[0] = nums[0];
    num[1] = Math.max(num[0], nums[1]);

    for (int i = 2; i < nums.length; i++) {
      num[i] = Math.max(num[i - 1], num[i - 2] + nums[i]);
    }

    return num[nums.length - 1];
  }

  public static void main(String[] args) {

    int[] arr = {2, 7, 9, 3, 1};

    final S198_HouseRobber rober = new S198_HouseRobber();

    System.out.println(rober.rob(arr));

    arr = new int[]{2, 1, 1, 2};
    System.out.println(rober.rob(arr));
  }
}
