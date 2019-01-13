package S201_300;

import model.TreeNode;

/**
 * Create by haifei on 13/1/2019.
 * <p>
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * <p>
 * 在二叉查找树中查找最近公共祖先节点
 */
public class S235_LowestCommonAncestorofaBinarySearchTree {

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

    if (root == null) {
      return null;
    }

    if (root == p) {
      boolean findQ = findNode(root.left, q) || findNode(root.right, q);
      if (findQ) {
        return root;
      }
    } else if (root == q) {
      boolean findP = findNode(root.left, p) || findNode(root.right, p);
      if (findP) {
        return root;
      }
    }

    if (findNode(root.left, p)) {
      if (findNode(root.right, q)) {
        return root;
      } else {
        return lowestCommonAncestor(root.left, p, q);
      }
    } else {
      if (findNode(root.left, q)) {
        return root;
      } else {
        return lowestCommonAncestor(root.right, p, q);
      }
    }
  }

  public boolean findNode(TreeNode head, TreeNode target) {

    if (head == null) {
      return false;
    }
    if (head.val == target.val) {
      return true;
    }

    if (head.val > target.val) {
      return findNode(head.left, target);
    } else {
      return findNode(head.right, target);
    }
  }

  public TreeNode lowestCommonAncestor_Recursion(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) {
      return null;
    }

    if (p.val < root.val && q.val < root.val) {
      return lowestCommonAncestor_Recursion(root.left, p, q);
    } else if (p.val > root.val && q.val > root.val) {
      return lowestCommonAncestor_Recursion(root.right, p, q);
    }

    return root;
  }

  public static void main(String[] args) {
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    TreeNode node6 = new TreeNode(6);
    TreeNode node7 = new TreeNode(7);
    TreeNode node8 = new TreeNode(8);
    TreeNode node9 = new TreeNode(9);
    TreeNode node0 = new TreeNode(0);

    node6.left = node2;
    node6.right = node8;

    node2.left = node0;
    node2.right = node4;

    node4.left = node3;
    node4.right = node5;

    node8.left = node7;
    node8.right = node9;

    final S235_LowestCommonAncestorofaBinarySearchTree searchTree =
      new S235_LowestCommonAncestorofaBinarySearchTree();

    System.out.println(searchTree.lowestCommonAncestor(node6, node2, node8).val);
    System.out.println(searchTree.lowestCommonAncestor(node6, node2, node4).val);

    System.out.println("简单递归解法");
    System.out.println(searchTree.lowestCommonAncestor_Recursion(node6, node2, node8).val);
    System.out.println(searchTree.lowestCommonAncestor_Recursion(node6, node2, node4).val);
  }





}
