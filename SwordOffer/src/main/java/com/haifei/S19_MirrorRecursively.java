package com.haifei;

import com.haifei.model.TreeNode;

/**
 * Create by haifei on 1/8/2018.
 */
public class S19_MirrorRecursively {

  private void MirrorRecursively(TreeNode node) {
    if (node == null || (node.left == null && node.right != null)) {
      return;
    }

    TreeNode temp = node.left;
    node.left = node.right;
    node.right = temp;

    if (node.left != null) {
      MirrorRecursively(node.left);
    }
    if (node.right != null) {
      MirrorRecursively(node.right);
    }
  }
}
