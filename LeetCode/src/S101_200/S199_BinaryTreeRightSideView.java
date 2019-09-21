package S101_200;

import model.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/binary-tree-right-side-view
 * Create by haifei on 12/9/2019 8:34 AM.
 */
public class S199_BinaryTreeRightSideView {

  public List<Integer> rightSideView(TreeNode root) {
    Set<Integer> set = new HashSet<>();
    List<Integer> ans = new ArrayList<>();

    dfsHelper(root, 1, set, ans);

    return ans;
  }

  private void dfsHelper(TreeNode root, int depth, Set<Integer> depthSet, List<Integer> list) {
    if (null == root) {
      return;
    }
    if (!depthSet.contains(depth)) {
      depthSet.add(depth);
      list.add(root.val);
    }
    dfsHelper(root.right, depth + 1, depthSet, list);
    dfsHelper(root.left, depth + 1, depthSet, list);
  }
}
