package com.tree.btree.algrothirm;

import com.haifei.model.TreeNode;

/**
 * Create by haifei on 9/1/2019 8:25 PM.
 */
public class constructBinaryTree {

  public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
    TreeNode root = reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    return root;
  }

  public TreeNode reConstructBinaryTree(int[] pre, int start, int end, int[] in, int inStart,
                                        int inEnd) {
    if (start > end || inStart > inEnd) {
      return null;
    }

    TreeNode node = new TreeNode(pre[start]);

    for (int i = inStart; i <= inEnd; i++) {
      if (pre[start] == in[i]) {
        node.left = reConstructBinaryTree(pre, start + 1, start + (i - inStart), in, inStart,
          inEnd - 1);
        node.right = reConstructBinaryTree(pre, end - (inEnd - i), end, in, i + 1, end);
      }
    }

    return node;
  }


  public TreeNode reConstructBinaryTree_2(int[] in, int inStart, int inEnd, int[] last,
                                          int lastStart, int lastEnd) {
    if (inStart > inEnd || lastStart > lastEnd) {
      return null;
    }

    TreeNode node = new TreeNode(last[lastEnd]);

    for (int i = inStart; i <= inEnd; i++) {

      if (in[i] == node.val) {
        node.left = reConstructBinaryTree_2(in, inStart, i - 1, last, lastStart,
          lastEnd - (inEnd - i) - 1);
        node.right = reConstructBinaryTree_2(in, i + 1, inEnd, last, lastEnd - (inEnd - i),
          lastEnd - 1);
      }
    }

    return node;
  }
}
