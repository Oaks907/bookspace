package com.haifei;

import com.haifei.model.TreeNode;

/**
 * Create by haifei on 24/6/2018.
 */
public class S06_BuilderTree {

  public static TreeNode buildTree(int[] preorder, int[] inorder) {
    TreeNode head = treeHelper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    return head;
  }

  private static TreeNode treeHelper(int[] preorder, int preStart, int preEnd,
                                     int[] inoder, int inStart, int inEnd ){
    if (preStart > preEnd || inStart > inEnd) {
      return null;
    }
    TreeNode node = new TreeNode(preorder[preStart]);
    int index = 0;
    for (int i = 0; i < inoder.length; i++) {
      if (node.val == inoder[i]) {
        index = i;
        break;
      }
    }
    int leftLen = index - inStart;
    node.left = treeHelper(preorder,preStart + 1, preStart + leftLen, inoder, inStart, index - 1);
    node.right = treeHelper(preorder, preStart + leftLen + 1, preEnd, inoder, index + 1, inEnd);
    return node;
  }

  public static void main(String[] args) {
    int[] preorder = {1, 2, 4, 7, 3, 5, 6, 8};
    int[] inorder = {4, 7, 2, 1, 5, 3, 8, 6};

  }

}
