package S501_600;

import model.TreeNode;
import org.junit.Test;

/**
 * Create by haifei on 24/3/2019 10:50 PM.
 * <p>
 * https://leetcode.com/problems/subtree-of-another-tree/
 */
public class S572_SubtreeofAnotherTree {

  public boolean isSubtree(TreeNode s, TreeNode t) {

    if (s == null && t == null) {
      return true;
    }
    if (s == null || t == null) {
      return false;
    }

    boolean left = false;
    boolean right = false;

    if (s.left != null){
      left = isSubtree(s.left, t);
    }
    if (s.right != null) {
      right = isSubtree(s.right, t);
    }

    return helper(s, t) || left || right;
  }

  private boolean helper(TreeNode s, TreeNode t) {
    if (s == null && t == null) {
      return true;
    }
    if (s == null || t == null) {
      return false;
    }

    if (s.val != t.val) {
      return false;
    }

    return helper(s.left, t.left) && helper(s.right, t.right);
  }

  @Test
  public void test() {
    TreeNode node1 = new TreeNode(1);
    TreeNode node1_1 = new TreeNode(1);
    TreeNode node1_2 = new TreeNode(1);

    node1.right = node1_1;

    final S572_SubtreeofAnotherTree anotherTree = new S572_SubtreeofAnotherTree();
    System.out.println(anotherTree.isSubtree(node1, node1_2));
  }
}
