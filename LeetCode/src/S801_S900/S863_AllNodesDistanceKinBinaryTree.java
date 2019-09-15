package S801_S900;

import model.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 * Create by haifei on 12/9/2019 11:33 PM.
 */
public class S863_AllNodesDistanceKinBinaryTree {

  // BFS + DFS

  Map<TreeNode, TreeNode> parent;

  public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
    parent = new HashMap<>();
    dfs(root, null);

    // 注意，由targett开始
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(null);
    queue.add(target);
    Set<TreeNode> seen = new HashSet<>();
    seen.add(null);
    seen.add(target);

    int dist = 0;
    while (!queue.isEmpty()) {
      TreeNode poll = queue.poll();
      if (poll == null) {
        if (dist == K) {
          List<Integer> list = new ArrayList<>();
          for (TreeNode item : queue) {
            list.add(item.val);
          }
          return list;
        }
        dist++;
        queue.offer(null);
      } else {
        if (!seen.contains(poll.left)) {
          seen.add(poll.left);
          queue.offer(poll.left);
        }
        if (!seen.contains(poll.right)) {
          seen.add(poll.right);
          queue.offer(poll.right);
        }
        TreeNode pollParent = parent.get(poll);
        if (!seen.contains(pollParent)) {
          seen.add(pollParent);
          queue.offer(pollParent);
        }
      }
    }
    return new ArrayList<>();
  }

  private void dfs(TreeNode node, TreeNode par) {
    if (node != null) {
      parent.put(node, par);
      dfs(node.left, node);
      dfs(node.right, node);
    }
  }

  @Test
  public void test() {

  }
}
