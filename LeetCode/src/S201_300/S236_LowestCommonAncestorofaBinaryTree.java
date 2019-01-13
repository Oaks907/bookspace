package S201_300;

import model.TreeNode;

/**
 * Create by haifei on 13/1/2019 7:07 PM.
 *
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 */
public class S236_LowestCommonAncestorofaBinaryTree {

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (null == root || p == root || q == root) {
      return root;
    }

    //p 在左边
    if (findNode(root.left, p)) {
      //p 在左边 且 q 在右边
      if (findNode(root.right, q)) {
        return root;
      } else {
        return lowestCommonAncestor(root.left, p, q);
      }
    } else {  // p 不在左边
      // p 不在左边， q 在左边
      if (findNode(root.left, q)) {
        return root;
      } else {
        return lowestCommonAncestor(root.right, p, q);
      }
    }
  }

  public boolean findNode(TreeNode head, TreeNode targetNode) {
    if (null == head) {
      return false;
    }

    if (head == targetNode) {
      return true;
    }

    boolean isSuccess = findNode(head.left, targetNode);
    if (!isSuccess) {
      isSuccess = findNode(head.right, targetNode);
    }

    return isSuccess;
  }


  public TreeNode lowestCommonAncestor_Recursion(TreeNode root, TreeNode p, TreeNode q) {

    if (null == root || p == root || q == root) {
      return root;
    }

    TreeNode left = lowestCommonAncestor_Recursion(root.left, p, q);
    TreeNode right = lowestCommonAncestor_Recursion(root.right, p, q);

    return left != null && right != null ? root : left == null ? right : left;
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

    final S236_LowestCommonAncestorofaBinaryTree binaryTree =
      new S236_LowestCommonAncestorofaBinaryTree();

    System.out.println(binaryTree.lowestCommonAncestor(node6, node2, node8).val);
    System.out.println(binaryTree.lowestCommonAncestor(node6, node2, node4).val);

    System.out.println("简单递归：");
    System.out.println(binaryTree.lowestCommonAncestor_Recursion(node6, node2, node8).val);
    System.out.println(binaryTree.lowestCommonAncestor_Recursion(node6, node2, node4).val);
  }
}
