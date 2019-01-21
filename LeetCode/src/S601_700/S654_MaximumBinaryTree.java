package S601_700;

import model.TreeNode;

/**
 * Create by haifei on 21/1/2019 9:37 PM.
 * https://leetcode.com/problems/maximum-binary-tree/
 */
public class S654_MaximumBinaryTree {

  public TreeNode constructMaximumBinaryTree(int[] nums) {
    if (null == nums || 0 == nums.length) {
      return null;
    }

    return recursion(nums, 0, nums.length - 1);
  }

  public TreeNode recursion(int[] nums, int start, int end) {
    if (start > end || start < 0 || end >= nums.length) {
      return null;
    }

    int max = Integer.MIN_VALUE;
    int index = 0;
    for (int i = start; i <= end; i++) {
      if (nums[i] > max) {
        index = i;
        max = nums[i];
      }
    }

    TreeNode node = new TreeNode(max);

    node.left = recursion(nums, start, index - 1);
    node.right = recursion(nums, index + 1, end);

    return node;
  }

  public static void main(String[] args) {

    final S654_MaximumBinaryTree maximumBinaryTree = new S654_MaximumBinaryTree();

    int[] nums = {3, 2, 1, 6, 0, 5};
    final TreeNode x = maximumBinaryTree.constructMaximumBinaryTree(nums);
    System.out.println(x.val);
  }
}
