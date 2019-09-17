package S101_200;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Create by haifei on 17/9/2019 11:59 PM.
 */
public class S117_PopulatingNextRightPointersinEachNodeII {
  public S116_PopulatingNextRightPointersinEachNode.Node connect_1(S116_PopulatingNextRightPointersinEachNode.Node root) {

    Queue<S116_PopulatingNextRightPointersinEachNode.Node> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
      int size = queue.size();

      while (size-- > 0) {
        S116_PopulatingNextRightPointersinEachNode.Node poll = queue.poll();
        if (null == poll) {
          continue;
        }
        if (poll.left != null) {
          queue.offer(poll.left);
        }
        if (poll.right != null) {
          queue.offer(poll.right);
        }
        if (size > 0) {
          poll.next = queue.peek();
        } else if (size == 0) {
          poll.next = null;
        }
      }
    }

    return root;
  }
}
