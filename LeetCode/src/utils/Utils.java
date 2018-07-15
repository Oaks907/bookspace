package utils;

/**
 * Create by haifei on 24/1/2018.
 */
public class Utils {

  public static void printArray(int[][] nums) {
    for (int i = 0; i < nums.length; i++) {
      for (int j = 0; j < nums[0].length; j++) {
        System.out.print(nums[i][j] + ", ");
      }
      System.out.println();
    }
  }
}
