package S601_700;

import utils.TreeNode;

/**
 * Create by haifei on 2/1/2018.
 */
public class S617_mergeTrees {

  public static void main(String[] args) {

  }

  public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
    if (t1 == null && t2 == null) {
      return null;
    }
    int val = (t1 == null ? 0 : t1.val) + (t2 == null ? 0 : t2.val);
    TreeNode node = new TreeNode(val);
    node.left = mergeTrees(t1 == null ? null : t1.left, t2 == null ? null : t2.left);
    node.right = mergeTrees(t1 == null ? null : t1.right, t2 == null ? null : t1.right);

    return node;
  }

}
