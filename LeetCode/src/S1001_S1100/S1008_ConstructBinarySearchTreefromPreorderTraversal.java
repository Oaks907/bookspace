package S1001_S1100;

import model.TreeNode;
import utils.PrintUtils;

/**
 * Create by haifei on 19/3/2019 1:13 PM.
 */
public class S1008_ConstructBinarySearchTreefromPreorderTraversal {

  /**
   *   复杂版
   */
  public TreeNode bstFromPreorder(int[] preorder) {

    if (null == preorder || preorder.length == 0) {
      return null;
    }

    return recursion(preorder, 0, preorder.length - 1);
  }

  private TreeNode recursion(int[] preorder, int start, int end) {

    if (start > end) {
      return null;
    }

    TreeNode node = new TreeNode(preorder[start]);

    int nextIndex = findNextIndex(preorder, start + 1, end, preorder[start]);

    if (nextIndex == -1) {
      node.left = recursion(preorder, start + 1, end);
    } else {
      node.left = recursion(preorder, start + 1, nextIndex - 1);
      node.right = recursion(preorder, nextIndex, end);
    }

    return node;
  }


  private int findNextIndex(int[] preorder, int start, int end, int value) {
    for (int i = start; i <= end; i++) {
      if (preorder[i] >= value) {
        return i;
      }
    }
    return -1;
  }


  /**
   * 简单版本
   * @param preorder
   * @return
   */
  public TreeNode bstFromPreorder2(int[] preorder) {

    if (null == preorder || preorder.length == 0) {
      return null;
    }

    TreeNode root = null;
    for (int i = 0; i < preorder.length; i++) {
      root = putVal(root, preorder[i]);
    }

    return root;
  }

  public TreeNode putVal(TreeNode node, int val) {
    if (null == node) {
      return new TreeNode(val);
    }

    if (val < node.val) {
      node.left = putVal(node.left, val);
    } else {
      node.right = putVal(node.right, val);
    }

    return node;
  }

  public static void main(String[] args) {
    int[] pre = {8, 5, 1, 7, 10, 12};
    final S1008_ConstructBinarySearchTreefromPreorderTraversal traversal = new S1008_ConstructBinarySearchTreefromPreorderTraversal();
    final TreeNode node = traversal.bstFromPreorder(pre);
    PrintUtils.printTree(node);
  }
}
