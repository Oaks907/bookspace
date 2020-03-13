package S1301_S1400;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

import model.TreeNode;

/**
 * Create by haifei on 13/3/2020 1:03 PM.
 */
public class S1372_LongestZigZagPathinaBinaryTree {

    int res = 1;

    /**
     * TLE 版本
     *
     * @param root
     * @return
     */
    public int longestZigZag_TLE(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {

            TreeNode poll = queue.poll();
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }
            helper(poll, new ArrayList<TreeNode>(), true);
            helper(poll, new ArrayList<TreeNode>(), false);
        }
        return res - 1;
    }

    private void helper(TreeNode node, List<TreeNode> list, boolean rightDir) {

        if (node == null) {
            res = Math.max(res, list.size());
            return;
        }

        if (node.val == 4) {
            System.out.println(list);
        }

        list.add(node);
        if (rightDir) {
            helper(node.left, new ArrayList<>(list), false);
        } else {
            helper(node.right, new ArrayList<>(list), true);
        }
    }

    public int longestZigZag(TreeNode root) {
        int[] dfs = dfs(root);
        return dfs[2];
    }

    private int[] dfs(TreeNode root) {
        if (null == root) {
            return new int[] {-1, -1, -1};
        }
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        int res = Math.max(Math.max(left[1], right[0]) + 1, Math.max(left[2], right[2]));

        return new int[] {left[1] + 1, right[0] + 1, res};
    }

    @Test
    public void test() {
        TreeNode node = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        node.right = node1;

        node1.left = node2;
        node1.right = node3;

        node3.left = node4;
        node3.right = node5;

        node4.right = node6;

        node6.right = node7;

        int res = longestZigZag(node);

        Assert.assertEquals(3, res);
    }
}
