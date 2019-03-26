package S101_200;

import model.TreeNode;

/**
 * Create by haifei on 26/3/2019 5:46 PM.
 * <p>
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 */
public class S108_ConvertSortedArraytoBinarySearchTree {


  public TreeNode sortedArrayToBST(int[] nums) {
    return helper(nums, 0, nums.length - 1);
  }

  public TreeNode helper(int[] nums, int low, int high) {
    if (low > high) {
      return null;
    }

    int mid = (low + high) >> 1;

    TreeNode tmp = new TreeNode(nums[mid]);
    tmp.left = helper(nums, low, mid - 1);
    tmp.right = helper(nums, mid + 1, high);
    return tmp;
  }
}
