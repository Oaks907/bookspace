package com.tree.btree.algrothirm;

import com.haifei.model.TreeNode;

import java.util.ArrayList;

/**
 * Create by haifei on 9/1/2019 9:25 PM.
 */
public class SearchResult {

  ArrayList<Integer> result;

  ArrayList<Integer> searchRange(TreeNode root, int k1, int k2) {
    result = new ArrayList<Integer>();
    searchHelper(root, k1, k2);
    return result;
  }

  private void searchHelper(TreeNode root, int k1, int k2) {
    if (root == null) {
      return;
    }

    if (root.val > k1) {
      searchHelper(root.left, k1, k2);
    }

    if (root.val > k1 && root.val < k2) {
      result.add(root.val);
    }

    if (root.val < k2) {
      searchHelper(root.right, k1, k2);
    }
  }
}
