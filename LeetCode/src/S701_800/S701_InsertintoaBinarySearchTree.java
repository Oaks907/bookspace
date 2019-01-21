package S701_800;

import model.TreeNode;

/**
 * Create by haifei on 21/1/2019 9:24 PM.
 * <p>
 * 二叉搜索树插入节点
 * https://leetcode.com/problems/insert-into-a-binary-search-tree/
 */
public class S701_InsertintoaBinarySearchTree {

  public TreeNode insertIntoBST(TreeNode root, int val) {
    if (root == null) {
      return root;
    }
    TreeNode head = root;

    while (root != null) {

      TreeNode node = null;

      if (root.val > val) {
        node = root.left;

        if (node == null) {
          root.left = new TreeNode(val);
        }

      } else if (root.val < val) {
        node = root.right;
        if (node == null) {
          root.right = new TreeNode(val);
        }
      }
      root = node;
    }

    return head;
  }

}
