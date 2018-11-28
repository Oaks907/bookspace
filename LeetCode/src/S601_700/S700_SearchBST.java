package S601_700;

import model.TreeNode;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Create by haifei on 28/11/2018 9:14 AM.
 */
public class S700_SearchBST {

  public static void main(String[] args) {

  }

  public TreeNode searchBST(TreeNode root, int val) {
    if (null == root) {
      return null;
    }

    if (root.val == val) {
      return root;
    }
    final TreeNode left = searchBST(root.left, val);
    final TreeNode right = searchBST(root.right, val);

    if (left != null) {
      return left;
    }
    if (right != null) {
      return right;
    }

    return null;
  }


  public TreeNode searchBST2(TreeNode root, int val) {
    if (null == root) {
      return null;
    }

    Queue<TreeNode> nodeQueue = new LinkedBlockingQueue<>();
    nodeQueue.add(root);

    while (!nodeQueue.isEmpty()) {
      TreeNode node = nodeQueue.poll();
      if (node.val == val) {
        return  node;
      }

      if (node.left != null)
        nodeQueue.add(node.left);
      if (node.right != null){
        nodeQueue.add(node.right);
      }
    }

    return null;
  }
}
