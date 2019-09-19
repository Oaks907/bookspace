package S601_700;

import model.TreeNode;
import org.junit.Before;
import org.junit.Test;
import utils.PrintUtils;

/**
 * https://leetcode.com/problems/add-one-row-to-tree/
 * Create by haifei on 11/9/2019 8:50 AM.
 */
public class S623_AddOneRowtoTree {

  public TreeNode addOneRow(TreeNode root, int v, int d) {
    depthLimit = d;
    nodeValue = v;

    if (d == 1) {
      TreeNode treeNode = new TreeNode(v);
      treeNode.left = root;
      return treeNode;
    }

    helper(root,1);
    return root;
  }

  private int depthLimit;
  private int nodeValue;

  public void helper(TreeNode node, int depth) {

    if (null == node) {
      return;
    }

    if (depth == depthLimit - 1) {
     TreeNode temp = node.left;
     node.left = new TreeNode(nodeValue);
     node.left.left = temp;

     temp = node.right;
     node.right = new TreeNode(nodeValue);
     node.right.right = temp;
    }

    helper(node.left, depth + 1);
    helper(node.right, depth + 1);
  }


  public TreeNode addOneRow2(TreeNode t, int v, int d) {
    if (d == 1) {
      TreeNode n = new TreeNode(v);
      n.left = t;
      return n;
    }
    insert(v, t, 1, d);
    return t;
  }

  public void insert(int val, TreeNode node, int depth, int n) {
    if (node == null)
      return;
    if (depth == n - 1) {
      TreeNode t = node.left;
      node.left = new TreeNode(val);
      node.left.left = t;
      t = node.right;
      node.right = new TreeNode(val);
      node.right.right = t;
    } else {
      insert(val, node.left, depth + 1, n);
      insert(val, node.right, depth + 1, n);
    }
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
    node4.left = node2;
    node4.right = node6;

    node2.left = node3;
    node2.right = node1;

    node6.left = node5;

    S623_AddOneRowtoTree oneRowToTree = new S623_AddOneRowtoTree();
    TreeNode result = oneRowToTree.addOneRow(node4, 1, 2);

    PrintUtils.printTree(result);
  }

  @Test
  public void test1() {
    node1.left = node2;
    node1.right = node3;
    node2.left = node4;

    S623_AddOneRowtoTree oneRowToTree = new S623_AddOneRowtoTree();
    TreeNode result = oneRowToTree.addOneRow(node1, 5, 4);

    PrintUtils.printTree(result);
  }
}
