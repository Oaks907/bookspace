package S101_200;

import model.TreeNode;
import org.junit.Test;
import utils.PrintUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * Create by haifei on 25/9/2019 8:33 AM.
 */
public class S106_ConstructBinaryTreefromInorderandPostorderTraversal {

  int post;

  public TreeNode buildTree(int[] inorder, int[] postorder) {

    post = postorder.length - 1;
    Map<Integer, Integer> map = new HashMap();
    for (int i = 0; i < inorder.length; i++) {
      map.put(inorder[i], i);
    }

    return helper(0, postorder.length - 1, map, postorder);
  }

  private TreeNode helper(int start, int end, Map<Integer, Integer> map, int[] postOrder) {
    if (start > end) {
      return null;
    }

    TreeNode node = new TreeNode(postOrder[post]);
    int nodeValue = postOrder[post];
    post--;

    node.right = helper(map.get(nodeValue) + 1, end, map, postOrder);
    node.left = helper(start, map.get(nodeValue) - 1, map, postOrder);

    return node;
  }

  @Test
  public void test() {
    int[] inorder = {9, 3, 15, 20, 7};
    int[] postOrder = {9, 15, 7, 20, 3};

    TreeNode node = buildTree(inorder, postOrder);

    PrintUtils.printTree(node);
  }
}
