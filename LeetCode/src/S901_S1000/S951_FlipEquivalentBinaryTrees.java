package S901_S1000;

import model.TreeNode;

/**
 * https://leetcode.com/problems/flip-equivalent-binary-trees/
 * Create by haifei on 26/8/2019 11:49 PM.
 */
public class S951_FlipEquivalentBinaryTrees {

  public boolean flipEquiv(TreeNode root1, TreeNode root2) {

    return helper(root1, root2);
  }

  private boolean helper(TreeNode root1, TreeNode root2) {
    if (root1 == null || root2 == null)
      return root1 == root2;
    return (root1.val == root2.val)
      && ((helper(root1.left, root2.left) && helper(root1.right, root2.right))
      || helper(root1.left, root2.right) && helper(root1.right, root2.left));
  }
}
