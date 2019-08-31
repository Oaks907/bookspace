package S901_S1000;

import model.TreeNode;
import org.junit.Before;
import org.junit.Test;
import utils.PrintUtils;
import utils.TreeUtils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Create by haifei on 31/8/2019 10:27 AM.
 */
public class S919_CompleteBinaryTreeInserter {

  TreeNode root;
  int level;
  int size;

  public S919_CompleteBinaryTreeInserter(TreeNode root) {
    this.root = root;
    size = getSize(root);
    level = getLevel(size);
  }

  public int insert(int v) {
    TreeNode node = new TreeNode(v);
    TreeNode canInsertNode = getCanInsertNode(root);
    if (canInsertNode.left == null) {
      canInsertNode.left = node;
    } else {
      canInsertNode.right = node;
    }
    size++;
    if (Math.pow(2, level) - 1 < size) {
      level++;
    }
    return canInsertNode.val;
  }

  public TreeNode get_root() {
    return root;
  }

  private int getSize(TreeNode node) {
    if (node == null) {
      return 0;
    }
    return getSize(node.left) + getSize(node.right) + 1;
  }

  private int getLevel(int size) {
    int level = 1;
    while (Math.pow(2, level) - 1 < size) {
      level++;
    }
    return level;
  }


  // 广度优先遍历获取插入的位置
  private TreeNode getCanInsertNode(TreeNode node) {

    TreeNode readyToInsertNode = null;

    // 是完全满二叉树
    if (size == Math.pow(2, level) - 1) {
      while (node != null) {
        readyToInsertNode = node;
        node = node.left;
      }
      return readyToInsertNode;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(node);
    int curLevel = 1;

    while (!queue.isEmpty()) {
      curLevel++;
      int queueSize = queue.size();

      if (curLevel >= level) {
        while (queueSize-- > 0) {
          TreeNode poll = queue.poll();
          if (poll.left == null || poll.right == null) {
            readyToInsertNode = poll;
            return readyToInsertNode;
          }
        }
      }

      while (queueSize-- > 0) {
        TreeNode poll = queue.poll();
        if (null != poll.left) {
          queue.offer(poll.left);
        }
        if (null != poll.right) {
          queue.offer(poll.right);
        }
      }
    }
    throw new IllegalArgumentException();
  }

  @Test
  public static void main(String[] args) {

    TreeNode node1 = new TreeNode(1);

    S919_CompleteBinaryTreeInserter completeBinaryTreeInserter =
      new S919_CompleteBinaryTreeInserter(node1);

    System.out.println(completeBinaryTreeInserter.insert(2));
    System.out.println(completeBinaryTreeInserter.insert(3));
    System.out.println(completeBinaryTreeInserter.insert(4));


    completeBinaryTreeInserter =
      new S919_CompleteBinaryTreeInserter(completeBinaryTreeInserter.get_root());
    System.out.println("----");
    PrintUtils.printTree(completeBinaryTreeInserter.get_root());
    System.out.println("----");
    System.out.println(completeBinaryTreeInserter.insert(5));
    System.out.println("----");
    PrintUtils.printTree(completeBinaryTreeInserter.get_root());
  }
}
