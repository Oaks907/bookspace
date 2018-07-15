package S001_100;

/**
 * Create by haifei on 30/1/2018.
 */
public class S088_MergeSortedArray {
  public static void merge(int[] nums1, int m, int[] nums2, int n) {
    if (nums2 == null || nums2.length < 0) {
      return;
    }

    int nums1Index = m - 1;
    int nums2Index = n - 1;
    for (int i = m + n - 1; i >= 0; i--) {
      if (nums2Index < 0) {
        break;
      }
      int value2 = nums2[nums2Index];
      if (nums1Index >= 0) {
        int value1 = nums1[nums1Index];
        if (value1 < value2) {
          nums1[i] = value2;
          nums2Index--;
        } else {
          nums1[i] = value1;
          nums1Index--;
        }
      } else {
        nums1[i] = value2;
        nums2Index--;
      }
    }

    for (int i = 0; i < nums1.length; i++) {
      System.out.print(" " + nums1[i]);
    }
    System.out.println();
  }

  public static void main(String[] args) {
    int nums1[] = {1, 3, 5, 7, 9, 0, 0, 0, 0, 0};
    int nums2[] = {2, 4, 6, 8, 10};
    merge(nums1, 5, nums2, 5);
    nums1 = new int[]{2, 0};
    nums2 = new int[]{1};
    merge(nums1, 1, nums2, 1);
  }
}
