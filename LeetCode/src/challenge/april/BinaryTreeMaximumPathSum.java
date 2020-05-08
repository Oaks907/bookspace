package challenge.april;

import org.junit.Assert;
import org.junit.Test;

import model.TreeNode;

/**
 * Create by haifei on 7/5/2020 9:13 PM.
 */
public class BinaryTreeMaximumPathSum {

    int result = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {

        helper(root);

        return result;
    }

    private int helper(TreeNode node) {

        if (null == node) {
            return 0;
        }

        int left = Math.max(0, helper(node.left));
        int right = Math.max(0, helper(node.right));

        /**
         * maxValue is the value which recording whether this current root is the final root, so we use left + right
         * + node.val. But to the upper layer(after return statement), we cannot choose both left and right brunches,
         * so we need to select the larger one, so we use max(left, right) + node.val to prune the lower brunch.
         * In the end, very elegant solution, thank you for your sharing!
         */
        result = Math.max(result, node.val + left + right);
        return Math.max(left, right) + node.val;
    }

    @Test
    public void test() {

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);

        node1.left = node2;
        node1.right = node3;

        int result = maxPathSum(node1);

        Assert.assertEquals(6, result);
    }

    @Test
    public void test1() {

        TreeNode node1 = new TreeNode(-10);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        int result = maxPathSum(node1);

        Assert.assertEquals(42, result);
    }
}
