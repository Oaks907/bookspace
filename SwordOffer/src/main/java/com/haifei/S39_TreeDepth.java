package com.haifei;

import com.haifei.model.TreeNode;

/**
 * Create by haifei on 13/11/2018 11:39 PM.
 */
public class S39_TreeDepth {

  public int treeDepth(TreeNode node) {
    if (null == node) {
      return 0;
    }
    int leftDepth = treeDepth(node.left);
    int rightDepth = treeDepth(node.right);

    return leftDepth > rightDepth ? (leftDepth + 1) : (rightDepth + 1);
  }
}
