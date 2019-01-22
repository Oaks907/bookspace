package S001_100;

import jdk.nashorn.internal.runtime.regexp.joni.ast.EncloseNode;

import java.util.logging.Level;

/**
 * Create by haifei on 20/1/2019 3:00 PM.
 * 旋转数组中搜索某个值
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 */
public class S033_SearchinRotatedSortedArray {

  public int search(int[] nums, int target) {

    if (null == nums || 0 == nums.length) {
      return -1;
    }

    int start = 0;
    int end = nums.length - 1;

    while (start < end) {

      int mid = start + (end - start) / 2;

      if (nums[start] == target) return start;
      if (nums[end] == target) return end;
      if (nums[mid] == target) return mid;

      if (nums[end] < nums[mid] && target > nums[mid]) {
        start = mid;
      } else if (nums[end] < nums[mid] && target < nums[mid] && target > nums[start]) {
        end = mid;
      } else if (nums[end] < nums[mid] && target < nums[mid] && target < nums[start]) {
        start = mid;
      } else if (nums[start] > nums[mid] && target < nums[mid]) {
        end = mid;
      } else if (nums[start] > nums[mid] && target > nums[mid] && target < nums[start]) {
        start = mid;
      } else if (nums[start] > nums[mid] && target > nums[mid] && target > nums[start]) {
        end = mid;
      } else if (nums[start] < nums[mid] && nums[end] > nums[mid] && target > nums[mid]) {
        start = mid;
      } else {
        end = mid;
      }
    }

    if (nums[start] == target) return start;
    if (nums[end] == target) return end;

    return -1;
  }

  public static void main(String[] args) {
    final S033_SearchinRotatedSortedArray sortedArray =
      new S033_SearchinRotatedSortedArray();

    int[] arr = {4, 5, 6, 7, 0, 1, 2};
    System.out.println(sortedArray.search(arr, 7));
    System.out.println(sortedArray.search(arr, 0));
  }
}
