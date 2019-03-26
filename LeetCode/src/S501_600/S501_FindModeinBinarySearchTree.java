package S501_600;

import model.TreeNode;
import org.junit.Test;
import utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by haifei on 19/3/2019 10:45 PM.
 * <p>
 * https://leetcode.com/problems/find-mode-in-binary-search-tree/
 */
public class S501_FindModeinBinarySearchTree {

  int max = Integer.MIN_VALUE;
  int curVal = 0;
  Integer preNum = null;

  public int[] findMode(TreeNode root) {

    if (null == root) {
      return new int[0];
    }

    List<Integer> list = new ArrayList<>();

    helper(root, list);

    int[] result = new int[list.size()];
    for (int i = 0; i < list.size(); i++) {
      result[i] = list.get(i);
    }

    return result;
  }

  public void helper(TreeNode node, List<Integer> list) {
    if (null == node) {
      return;
    }

    helper(node.left, list);

    //记录前一个节点 pre 。更新当前node的value值出现的次数 curVal
    if (preNum == null || node.val > preNum) {
      preNum = node.val;
      curVal = 1;
    } else if (node.val == preNum) {
      curVal++;
    }

    if (curVal > max) {
      list.clear();
      list.add(node.val);
      max = curVal;
    } else if (curVal == max) {
      list.add(node.val);
    }


    helper(node.right, list);
  }

  @Test
  public void test() {
    TreeNode node1 = new TreeNode(1);
    TreeNode node2_1 = new TreeNode(2);
    TreeNode node2_2 = new TreeNode(2);

    node1.right = node2_1;
    node2_1.left = node2_2;

    final S501_FindModeinBinarySearchTree searchTree =
      new S501_FindModeinBinarySearchTree();

    PrintUtils.printArray(searchTree.findMode(node1));
  }

  @Test
  public void test1() {
    TreeNode node1 = new TreeNode(1);
    TreeNode node1_1 = new TreeNode(1);
    TreeNode node1_2 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node3_1 = new TreeNode(3);
    TreeNode node3_2 = new TreeNode(3);

    node2.left = node1;
    node2.right = node3;

    node1.left = node1_1;
    node1.right = node1_2;

    node3.left = node3_1;
    node3.right = node3_2;

    final S501_FindModeinBinarySearchTree searchTree =
      new S501_FindModeinBinarySearchTree();

    PrintUtils.printArray(searchTree.findMode(node2));
  }
}
