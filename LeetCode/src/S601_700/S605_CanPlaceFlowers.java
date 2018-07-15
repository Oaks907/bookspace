package S601_700;

import java.util.Arrays;

/**
 * Create by haifei on 18/1/2018.
 */
public class S605_CanPlaceFlowers {
  public static boolean canPlaceFlowers(int[] flowerbed, int n) {

    int count = 0;
    int[] realFlowerbed = new int[flowerbed.length + 2];
    realFlowerbed[0] = 0;
    realFlowerbed[flowerbed.length + 1] = 0;
    for (int i = 0; i < flowerbed.length; i++) {
      realFlowerbed[i + 1] = flowerbed[i];
    }

    for (int i = 1; i < realFlowerbed.length - 1; i++) {
      if (realFlowerbed[i] == 0 && realFlowerbed[i - 1] == 0 && realFlowerbed[i + 1] == 0) {
        realFlowerbed[i] = 1;
        if (++count >= n) {
          return true;
        }
      }
    }
    return false;
  }


  public static boolean canPlaceFlowers1(int[] flowerbed, int n) {
    if (n == 0) {
      return true;
    }

    int count = 0;
    if (flowerbed.length > 1 && flowerbed[0] == 0 && flowerbed[1] == 0) {
      count++;
      flowerbed[0] = 1;
    }

    if (flowerbed.length > 2 && flowerbed[flowerbed.length - 1] == 0 && flowerbed[flowerbed.length - 2] == 0) {
      count++;
      flowerbed[flowerbed.length - 1] = 1;
    }

    for (int i = 1; i < flowerbed.length - 1; i++) {
      if (flowerbed[i] != 1 && flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0) {
        flowerbed[i] = 1;
        if (++count >= n) {
          return true;
        }
      }
    }

    if (count > n) {
      return false;
    }

      return false;
  }

  public static void main(String[] args) {
    int nums[] = {1, 0, 0, 0, 1};
    int nums1[] = {1, 0, 0, 0, 1, 0, 0};
    System.out.println(canPlaceFlowers(nums, 1)); //true
    System.out.println(canPlaceFlowers(nums, 2)); //false
    System.out.println(canPlaceFlowers(nums1, 2)); // true;

    System.out.println(canPlaceFlowers1(nums, 1)); //true
    System.out.println(canPlaceFlowers1(nums, 2)); //false
    System.out.println(canPlaceFlowers1(nums1, 2)); // true;
  }

  /**
   * Create by haifei on 18/1/2018.
   */
  public static class S628_MaximumProductOfThreeNumbers {

    public int maximumProduct(int[] nums) {
      Arrays.sort(nums);
      int a = nums[0] * nums[1] * nums[nums.length - 1];
      int b = nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3];
      return Math.max(a, b);
    }
  }
}
