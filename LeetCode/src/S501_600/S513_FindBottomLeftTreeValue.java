package S501_600;

import model.TreeNode;

/**
 * Create by haifei on 29/8/2019 11:44 PM.
 */
public class S513_FindBottomLeftTreeValue {

  public int findBottomLeftValue(TreeNode root) {
    helper(root, 0);
    return result;
  }

  private int maxDepth = 0;
  int result = 0;

  private void helper(TreeNode node, int depth) {
    if (null == node) {
      return;
    }

    if (depth >= maxDepth) {
      maxDepth = depth;
      result = node.val;
    }

    helper(node.right, depth + 1);
    helper(node.left, depth + 1);
  }

  public static void main(String[] args) {

    TreeNode node0 = new TreeNode(0);
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);

    node0.left = node1;
    node0.right = node2;

    node2.right = node3;

    S513_FindBottomLeftTreeValue leftTreeValue = new S513_FindBottomLeftTreeValue();
    System.out.println(leftTreeValue.findBottomLeftValue(node0));
  }
}
