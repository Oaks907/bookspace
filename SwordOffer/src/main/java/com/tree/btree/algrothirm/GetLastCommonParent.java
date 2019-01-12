package com.tree.btree.algrothirm;

import com.haifei.model.TreeNode;

/**
 * Create by haifei on 9/1/2019 3:13 PM.
 * 求最低公共祖先节点。这个节点
 */
public class GetLastCommonParent {

  public TreeNode getLastCommonParent(TreeNode root, TreeNode t1, TreeNode t2) {

    if (findNode(root.left, t1)) {
      if (findNode(root.right, t2)) {
        return root;
      } else {
        return getLastCommonParent(root.left, t1, t2);
      }
    } else {
      if (findNode(root.left, t2)) {
        return root;
      } else {
        return getLastCommonParent(root.right, t1, t2);
      }
    }
  }

  public boolean findNode(TreeNode root, TreeNode node) {
    if (null == root) {
      return false;
    }

    if (root.val == node.val) {
      return true;
    }

    boolean findSuccess = findNode(root.left, node);
    if (!findSuccess) {
      findSuccess = findNode(root.right, node);
    }

    return findSuccess;
  }
}
