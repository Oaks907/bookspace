package S601_700;

import model.TreeNode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create by haifei on 9/9/2019 7:56 AM.
 */
public class S655_PrintBinaryTree {

  public List<List<String>> printTree(TreeNode root) {
    int hight = getHighForTree(root);
    String[][] res = new String[hight][(1 << hight) - 1];
    for (String[] arr : res) {
      Arrays.fill(arr, "");
    }

    helper(root, res, 0, 0, res[0].length);

    List<List<String>> ans = new ArrayList<>();
    for(String[] arr:res)
      ans.add(Arrays.asList(arr));
    return ans;
  }

  private void helper(TreeNode node, String[][] res, int row, int left, int right) {
    if (null == node) {
      return;
    }
    res[row][(left + right) / 2] = String.valueOf(node.val);
    helper(node.left, res, row + 1, left, (left + right) / 2);
    helper(node.right, res, row + 1, (left + right) / 2 + 1, right);
  }

  private int getHighForTree(TreeNode node) {
    if (null == node) {
      return 0;
    }
    return Math.max(getHighForTree(node.left), getHighForTree(node.right)) + 1;
  }

  TreeNode node1;
  TreeNode node2;
  TreeNode node3;
  TreeNode node4;
  TreeNode node5;
  TreeNode node6;
  TreeNode node7;
  TreeNode node8;

  @Before
  public void before() {
    node1 = new TreeNode(1);
    node2 = new TreeNode(2);
    node3 = new TreeNode(3);
    node4 = new TreeNode(4);
    node5 = new TreeNode(5);
    node6 = new TreeNode(6);
    node7 = new TreeNode(7);
    node8 = new TreeNode(8);
  }

  @Test
  public void test() {
    node1.left = node2;
    node1.right = node5;

    node2.left = node3;
    node3.left = node4;

    S655_PrintBinaryTree printBinaryTree = new S655_PrintBinaryTree();
    System.out.println(printBinaryTree.printTree(node1));
  }
}
