package S001_100;

import org.junit.Test;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 * Create by haifei on 24/2/2020 5:12 PM.
 */
public class S081_SearchinRotatedSortedArrayII {

    public boolean search(int[] nums, int target) {

        int left = 0;
        int right = nums.length;

        while (left < right) {

            int middle = left + (right - left) / 2;

            int midVal = nums[middle];
            if (midVal == target) {
                return true;
            }

            if (midVal > nums[left]) { // 正常升序
                if (target < midVal && target >= nums[left]) {
                    right = middle;
                } else {
                    left = middle + 1;
                }
            } else if (midVal < nums[left]){
                if (target > midVal && target < nums[left]) {
                    left = middle + 1;
                } else {
                    right = middle;
                }
            } else {
                left++;
            }
        }

        return false;
    }

    @Test
    public void test() {
        int[] arr = {};

        search(arr, 5);
    }
}
