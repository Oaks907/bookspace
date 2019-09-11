package S601_700;

import model.TreeNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

/**
 * Create by haifei on 10/9/2019 7:18 AM.
 */
public class S662_MaximumWidthofBinaryTree {

  // 广度优先搜索，但是会Time limit exceeded
  public int widthOfBinaryTree(TreeNode root) {

    int maxWidth = 0;
    if (root != null) {
      maxWidth = 1;
    }

    LinkedList<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      int size = queue.size();
      while (size-- > 0) {
        TreeNode poll = queue.poll();
        if (null == poll) {
          queue.offer(null);
          queue.offer(null);
          continue;
        }
        if (poll.left != null) {
          queue.offer(poll.left);
        } else {
          queue.offer(null);
        }
        if (poll.right != null) {
          queue.offer(poll.right);
        } else {
          queue.offer(null);
        }
      }

      int left = 0, right = queue.size() - 1;

      while (left < right) {
        TreeNode leftNode = queue.get(left);
        TreeNode rightNode = queue.get(right);
        if (leftNode == null) {
          left++;
        }
        if (rightNode == null) {
          right--;
        }
        if (leftNode != null && rightNode != null) {
          break;
        }
      }

      maxWidth = Math.max(maxWidth, right > left ? (right - left + 1) : (right - left));
//      System.out.println(queue + "left=" + left + ",right=" + right);
      if (!isNotAllNull(queue)) {
        return maxWidth;
      }
    }

    return maxWidth;
  }


  // 递归解法
  public int widthOfBinaryTree1(TreeNode root) {
    result = 0;
    left = new HashMap<>();
    dfs(root, 0, 0);
    return  result;
  }

  int result = 0;
  Map<Integer, Integer> left;

  public void dfs(TreeNode node, int depth, int pos) {
    if (null == node) {
      return;
    }
    left.computeIfAbsent(depth, x -> pos);
    result = Math.max(result, pos - left.get(depth) + 1);
    dfs(node.left, depth + 1, pos * 2);
    dfs(node.right, depth + 1, pos * 2 + 1);
  }

  boolean isNotAllNull(LinkedList<TreeNode> queue) {
    return queue.stream().distinct().anyMatch(Objects::nonNull);
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
    S662_MaximumWidthofBinaryTree widthofBinaryTree =
      new S662_MaximumWidthofBinaryTree();

    node1.left = node2;
    node1.right = node3;

    node2.left = node4;

    node3.right = node5;

    int result = widthOfBinaryTree(node1);

    Assert.assertEquals(4, result);
  }

  @Test
  public void test1() {
    S662_MaximumWidthofBinaryTree widthofBinaryTree =
      new S662_MaximumWidthofBinaryTree();

    node1.left = node2;
    node1.right = null;

    node2.left = node4;
    node2.right = node5;

    int result = widthOfBinaryTree(node1);

    Assert.assertEquals(2, result);
  }
}
