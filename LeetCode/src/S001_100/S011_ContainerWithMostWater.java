package S001_100;

/**
 * Create by haifei on 3/2/2018.
 */
public class S011_ContainerWithMostWater {
  public static int maxArea(int[] height) {
    int result = Integer.MIN_VALUE;

    int left = 0;
    int right = height.length - 1;
    while (left < right) {
      result = Math.max(result, Math.min(height[left], height[right]) * (right - left));
      if (height[left] < height[right]) {
        left++;
      } else {
        right--;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    int nums1[] = {1, 2, 4, 3};
    System.out.println(maxArea(nums1));
  }
}
