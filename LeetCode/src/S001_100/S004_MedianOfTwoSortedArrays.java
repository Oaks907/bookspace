package S001_100;

/**
 * Create by haifei on 23/1/2018.
 */
public class S004_MedianOfTwoSortedArrays {

  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
      return 0.0;
    }
    int m = nums1.length;
    int n = nums2.length;
    int left = (m + n + 1) / 2;
    int right = (m + n + 2) / 2;
    return (getKth(nums1, 0, nums2, 0, left) + getKth(nums2, 0, nums2, 0, right)) / 2.0;
  }

  /**
   * @param nums1
   * @param start1
   * @param nums2
   * @param start2
   * @param k      当前中位数
   * @return
   */
  private double getKth(int[] nums1, int start1, int[] nums2, int start2, int k) {
    if (start1 > nums1.length - 1) {
      return nums2[start2 + k - 1];
    }
    if (start2 > nums2.length - 1) {
      return nums1[start1 + k - 1];
    }

    if (k == 1) {
      return Math.min(nums1[start1], nums2[start2]);
    }

    int mid1 = Integer.MAX_VALUE;
    int mid2 = Integer.MAX_VALUE;
    if (start1 + k / 2 - 1 < nums1.length) {
      mid1 = nums1[start1 + k / 2 - 1];
    }
    if (start2 + k / 2 - 1 < nums2.length) {
      mid2 = nums2[start2 + k / 2 - 1];
    }
    if (mid1 < mid2) {
      return getKth(nums1, start1 + k / 2, nums2, start2, k - k / 2);
    } else {
      return getKth(nums1, start1, nums2, start2 + k / 2, k - k / 2);
    }
  }
}
