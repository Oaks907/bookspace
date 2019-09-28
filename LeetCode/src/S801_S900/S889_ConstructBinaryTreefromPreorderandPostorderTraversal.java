package S801_S900;

import model.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by haifei on 29/8/2019 8:47 AM.
 */
public class S889_ConstructBinaryTreefromPreorderandPostorderTraversal {

  public TreeNode constructFromPrePost(int[] pre, int[] post) {
    if (pre == null || post == null || pre.length != post.length)
      return null;

    int len = pre.length;
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < len; i++) {
      map.put(post[i], i);
    }
    return helper(pre, map, 0, len - 1, 0, len - 1);
  }

  private TreeNode helper(int[] pre, Map<Integer, Integer> map, int preLeft, int preRight,
                          int postLeft, int postRight) {
    if (preLeft > preRight) {
      return null;
    }

    if (preLeft == preRight) {
      return new TreeNode(pre[preLeft]);
    }

    TreeNode root = new TreeNode(pre[preLeft]);

    int leftR = map.get(pre[preLeft + 1]);
    int leftLen = leftR - postLeft + 1;

    root.left = helper(pre, map, preLeft + 1, preLeft + leftLen, postLeft, leftR);
    root.right = helper(pre, map, preLeft + 1 + leftLen, preRight, leftR + 1, postRight - 1);

    return root;
  }
}
