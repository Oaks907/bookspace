package S601_700;

import model.TreeNode;

import java.util.Set;
import java.util.TreeSet;

/**
 * Create by haifei on 23/3/2019 7:47 PM.
 * Given a Binary Search Tree and a target number, return true if there exist two elements in the
 * BST such that their sum is equal to the given target.
 *
 * https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
 */
public class S653_TwoSumIVnputisaBST {

  Set<Integer> set = new TreeSet<>();

  public boolean findTarget(TreeNode root, int k) {

    if (root == null) {
      return false;
    }

    int value = k - root.val;

    if (set.contains(value)) {
      return true;
    }
    set.add(root.val);

    return findTarget(root.left, k) || findTarget(root.right, k);
  }


}
