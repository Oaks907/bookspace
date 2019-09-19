package S101_200;

import model.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/find-duplicate-subtrees/
 * Create by haifei on 19/9/2019 11:43 PM.
 */
public class S162_FindDuplicateSubtrees {

  private Map<String, Integer> countMap = new HashMap<>();
  List<TreeNode> result = new ArrayList<>();

  public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
    collect(root);
    return result;
  }

  private String collect(TreeNode node) {
    if (null == node) {
      return "#";
    }
    String key = node.val + "," + collect(node.left) + "," + collect(node.right);
    countMap.put(key, countMap.getOrDefault(key, 0) + 1);
    if (countMap.get(key) == 2) {
      result.add(node);
    }
    return key;
  }
}
