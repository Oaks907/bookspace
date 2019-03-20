package S801_S900;

import model.TreeNode;
import utils.PrintUtils;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Create by haifei on 19/1/2019 11:48 AM.
 * https://leetcode.com/problems/increasing-order-search-tree/
 * <p>
 * 5
 * / \
 * 3    6
 * / \    \
 * 2   4    8
 * /        / \
 * 1        7   9
 * <p>
 * 1
 * \
 * 2
 * \
 * 3
 * \
 * 4
 * \
 * 5
 * \
 * 6
 * \
 * 7
 * \
 * 8
 * \
 * 9
 */
public class S897_IncreasingOrderSearchTree {

  /**
   * 空间使用达到限制
   *
   * @param root
   * @return
   */
  public TreeNode increasingBST(TreeNode root) {

    if (root == null) {
      return null;
    }

    Stack<TreeNode> stack = new Stack<>();
    List<TreeNode> list = new ArrayList<>();

    while (root != null || !stack.isEmpty()) {

      if (root != null) {
        stack.push(root);
        root = root.left;
      } else {
        final TreeNode pop = stack.pop();
        root = pop.right;
        list.add(pop);
      }
    }

    TreeNode head = list.get(0);

    for (int i = 0; i < list.size() - 1; i++) {
      TreeNode node = list.get(i);
      node.left = null;
      node.right = list.get(i + 1);
    }

    return head;
  }

  /**
   * 第二种解法
   * 记录前置节点
   *
   * @param root
   * @return
   */
  public TreeNode increasingBST_Recursion(TreeNode root) {
    if (null == root) {
      return root;
    }
    TreeNode head = root;
    while (head.left != null) {
      head = head.left;
    }

    helpRecursion(root);

    return head;
  }

  TreeNode prev = null, head = null;

  public void helpRecursion(TreeNode root) {
    if (root == null) {
      return;
    }

    helpRecursion(root.left);

    if (prev != null) {
      root.left = null; // we no  longer needs the left  side of the node, so set it to null
      prev.right = root;
    }

    if (head == null) head = root; // record the most left node as it will be our root
    prev = root; //keep track of the prev node

    helpRecursion(root.right);
  }

  /**
   * 第二种方法的非递归解法
   *
   * @param root
   * @return
   */
  public TreeNode increasingBST_Stack(TreeNode root) {

    if (root == null) {
      return null;
    }

    Stack<TreeNode> stack = new Stack<>();
    TreeNode prev = null, head = null;

    while (root != null || !stack.isEmpty()) {

      if (root != null) {
        stack.push(root);
        root = root.left;
      } else {
        final TreeNode pop = stack.pop();
        root = pop.right;

        if (head == null) {
          head = pop;
        }

        if (null != prev) {
          pop.left = null;
          prev.right = pop;
        }

        prev = pop;
      }
    }

    return head;
  }


  public static void main(String[] args) {

    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    TreeNode node6 = new TreeNode(6);

    node1.left = node2;
    node1.right = node3;
    node2.left = node4;
    node2.right = node5;
    node3.left = node6;

    final S897_IncreasingOrderSearchTree searchTree =
      new S897_IncreasingOrderSearchTree();
    TreeNode node = searchTree.increasingBST_Stack(node1);

    while (node != null) {
      System.out.print(node.val + " ");
      node = node.right;
    }
  }
}
