package utils;

/**
 * Create by haifei on 24/1/2018.
 */
public class Utils {

  private static final int MAXIMUM_CAPACITY = 10000;

  public static void printArray(int[][] nums) {
    for (int i = 0; i < nums.length; i++) {
      for (int j = 0; j < nums[0].length; j++) {
        System.out.print(nums[i][j] + ", ");
      }
      System.out.println();
    }
  }

  public static void printArray(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
        System.out.print(nums[i] + ", ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    System.out.println(tableSizeFor(8));
  }

  static final int tableSizeFor(int cap) {
    int n = cap - 1;
    n |= n >>> 1;
    n |= n >>> 2;
    n |= n >>> 4;
    n |= n >>> 8;
    n |= n >>> 16;
    return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
  }
}
