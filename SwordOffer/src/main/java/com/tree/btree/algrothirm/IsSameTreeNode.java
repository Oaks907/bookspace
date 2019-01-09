package com.tree.btree.algrothirm;

import com.haifei.model.TreeNode;

/**
 * Create by haifei on 8/1/2019 7:51 PM.
 */
public class IsSameTreeNode {

  /**
   * 判断两个树是否相同
   *
   * @param t1
   * @param t2
   * @return
   */
  public boolean isSameTree(TreeNode t1, TreeNode t2) {

    if (t1 == null && null == t2) {
      return true;
    }

    if (t1 == null || null == t2) {
      return false;
    }

    return t1.val == t2.val && (isSameTree(t1.left, t2.left) && isSameTree(t1.right, t2.right));
  }

  /**
   * 判断是否互为镜像
   *
   * @param t1
   * @param t2
   * @return
   */
  public boolean isMirror(TreeNode t1, TreeNode t2) {
    if (t1 == null && null == t2) {
      return true;
    }

    if (t1 == null || null == t2) {
      return false;
    }

    if (t1.val != t2.val) {
      return false;
    }

    return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
  }


  /**
   * 反转二叉树，镜像二叉树
   *
   * @param root
   * @return
   */
  TreeNode mirrorTreeNode(TreeNode root) {
    if (root == null) {
      return null;
    }
    TreeNode left = mirrorTreeNode(root.left);
    TreeNode right = mirrorTreeNode(root.right);

    root.left = right;
    root.right = left;
    return root;
  }
}
