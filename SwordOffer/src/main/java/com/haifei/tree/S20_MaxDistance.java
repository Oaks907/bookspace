package com.haifei.tree;

import com.haifei.model.TreeNode;

/**
 * Create by haifei on 18/11/2018 12:03 AM.
 */
public class S20_MaxDistance {

  private static class Result {
    int maxDistance;
    int maxDepth;

    public Result() {
    }

    public Result(int maxDistance, int maxDepth) {
      this.maxDistance = maxDistance;
      this.maxDepth = maxDepth;
    }
  }

  int getMaxDistance(TreeNode root) {
    return getMaxDistanceResult(root).maxDistance;
  }

  Result getMaxDistanceResult(TreeNode root) {
    if (root == null) {
      Result empty = new Result(0, -1);
      return empty;
    }
    Result lmd = getMaxDistanceResult(root.left);
    Result rmd = getMaxDistanceResult(root.right);
    Result result = new Result();
    result.maxDepth = Math.max(lmd.maxDepth, rmd.maxDepth) + 1;
    result.maxDistance = Math.max(lmd.maxDepth + rmd.maxDepth, Math.max(lmd.maxDistance,
      rmd.maxDistance));
    return result;
  }
}
