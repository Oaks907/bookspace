package S901_S1000;

import model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Check Completeness of a Binary Tree
 * Create by haifei on 12/9/2019 8:51 AM.
 */
public class S985_CheckCompletenessofaBinaryTree {

  public boolean isCompleteTree(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
      TreeNode remove = queue.remove();
      if (remove == null) {
        while (!queue.isEmpty()) {
          TreeNode tmp = queue.remove();
          if (null != tmp) {
            return false;
          }
        }
        return true;
      }

      queue.add(remove.left);
      queue.add(remove.right);
    }

    return true;
  }
}
