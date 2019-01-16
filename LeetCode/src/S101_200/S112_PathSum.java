package S101_200;

import model.TreeNode;

import java.util.Stack;

/**
 * Create by haifei on 16/1/2019 9:11 PM.
 * 是否存在由根节点到叶子节点的值等于某一个值
 * https://leetcode.com/problems/path-sum/
 */
public class S112_PathSum {

  public boolean hasPathSum(TreeNode root, int sum) {

    if (null == root) {
      return false;
    }

    return helper(root, 0, sum);
  }

  private boolean helper(TreeNode root, int curValue, int sum) {

    if (null == root) {
      return false;
    }

    curValue += root.val;

    if (root.left == null && root.right == null) {
      if (curValue == sum) {
        return true;
      } else {
        return false;
      }
    }

    return helper(root.left, curValue, sum) || helper(root.right, curValue, sum);
  }


  /**
   * 第二种解法，不使用递归,使用深度优先搜索DFS
   *
   * @param root
   * @param sum
   * @return
   */
  public boolean hasPathSum_DFS(TreeNode root, int sum) {

    if (root == null) {
      return false;
    }

    Stack<TreeNode> stack = new Stack<>();

    int curSum = 0;

    while (root != null || !stack.isEmpty()) {

      while (root != null) {
        curSum += root.val;
        stack.push(root);
        root = root.left;
      }

      final TreeNode pop = stack.pop();
      root = pop.right;

      if (pop.left == null && pop.right == null) {
        if (curSum == sum) {
          return true;
        } else {
          curSum -= pop.val;
        }
      }
    }
    return false;
  }

  public void reverse(TreeNode root){

  }


  public static void main(String[] args) {

    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);

    node1.left = node2;
    node1.right = node3;
    node2.left = node4;
    node2.right = node5;

    final S112_PathSum pathSum = new S112_PathSum();

    System.out.println(pathSum.hasPathSum(node1, 7));
    System.out.println(pathSum.hasPathSum(node1, 6));

    System.out.println(pathSum.hasPathSum_DFS(node1, 7));
    System.out.println(pathSum.hasPathSum_DFS(node1, 6));
  }
}
