package S801_S900;

import model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/all-possible-full-binary-trees/
 * Create by haifei on 19/9/2019 11:53 PM.
 */
public class S894_AllPossibleFullBinaryTrees {

  public List<TreeNode> allPossibleFBT(int N) {

    List<TreeNode> list = new ArrayList<>();
    if (N % 2 == 0) {
      return list;
    }
    if (N == 1) {
      list.add(new TreeNode(0));
      return list;
    }

    for (int leftNum = 1; leftNum < N - 1; leftNum += 2) {
      List<TreeNode> fLeft = allPossibleFBT(leftNum);
      List<TreeNode> fRight = allPossibleFBT(N - leftNum - 1);

      for (TreeNode left : fLeft) {
        for (TreeNode right : fRight) {
          TreeNode node = new TreeNode(0);
          node.left = left;
          node.right = right;
          list.add(node);
        }
      }
    }

    return list;
  }
}
