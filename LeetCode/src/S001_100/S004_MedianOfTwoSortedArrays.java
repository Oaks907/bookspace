package S001_100;

import org.junit.Test;

/**
 * Create by haifei on 23/1/2018.
 */
public class S004_MedianOfTwoSortedArrays {

  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int n = nums1.length;
    int m = nums2.length;

    int l = (m + n) % 2 == 0 ? (m + n) / 2 + 1 : (m + n + 1) / 2;

    int last = 0;
    int secondLast = 0;

    short p = 0, q = 0;

    for (short i = 0; i < l; i++) {
      secondLast = last;

      if (p >= n) {
        last = nums2[q++];
      } else if (q >= m) {
        last = nums1[p++];
      } else if (nums1[p] <= nums2[q]) {
        last = nums1[p++];
      } else {
        last = nums2[q++];
      }
    }

    if ((n + m) % 2 == 0) {
      return (last + secondLast) / 2.0;
    } else {
      return last;
    }
  }

  @Test
  public void test() {
    int[] nums1 = {1, 3};
    int[] nums2 = {2};

    System.out.println(findMedianSortedArrays(nums1, nums2));
  }
}
