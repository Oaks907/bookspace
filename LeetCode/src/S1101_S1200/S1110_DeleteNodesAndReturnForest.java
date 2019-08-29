package S1101_S1200;

import model.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Create by haifei on 28/8/2019 12:53 AM.
 */
public class S1110_DeleteNodesAndReturnForest {

  public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {


    for (int i : to_delete) {
      deleteSet.add(i);
    }

    helper(root, true);

    return result;
  }

  List<TreeNode> result = new ArrayList<>();
  Set<Integer> deleteSet = new HashSet<>();

  private TreeNode helper(TreeNode root, boolean isRoot) {
    if (null == root) {
      return null;
    }

    boolean deleted = deleteSet.contains(root.val);

    if (isRoot && !deleted) {
      result.add(root);
    }
    TreeNode left = helper(root.left, deleted);
    TreeNode right = helper(root.right, deleted);

    root.left = left;
    root.right = right;

    return deleted ? null : root;
  }

  public static void main(String[] args) {
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    TreeNode node6 = new TreeNode(6);
    TreeNode node7 = new TreeNode(7);

    node1.left = node2;
    node1.right = node3;

    node2.left = node4;
    node2.right = node5;

    node3.left = node6;
    node3.right = node7;

    S1110_DeleteNodesAndReturnForest returnForest =
      new S1110_DeleteNodesAndReturnForest();
    System.out.println(returnForest.delNodes(node1, new int[]{3, 5}));
  }
}
