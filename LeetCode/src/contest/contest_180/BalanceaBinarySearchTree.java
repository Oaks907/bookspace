package contest.contest_180;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

import model.TreeNode;
import utils.PrintUtils;

/**
 * Create by haifei on 15/3/2020 11:09 AM.
 */
public class BalanceaBinarySearchTree {

    public TreeNode balanceBST(TreeNode root) {

        // 先序遍历取出所有元素
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

        while (!stack.isEmpty() || node != null) {

            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            if (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                list.add(pop.val);
                node = pop.right;
            }
        }

        Integer[] arr = list.toArray(new Integer[0]);
        return buildBalanceTreeByList(arr, 0, list.size() - 1);
    }

    /**
     * 根据排序好的数据构建平衡二叉搜索树
     */
    public TreeNode buildBalanceTreeByList(Integer[] arr, int left, int right) {
        if (left > right) {
            return null;
        }

        int curIndex = (left + right) / 2;
        TreeNode node = new TreeNode(arr[curIndex]);
        node.left = buildBalanceTreeByList(arr, left, curIndex - 1);
        node.right = buildBalanceTreeByList(arr, curIndex + 1, right);
        return node;
    }

    @Test
    public void test() {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);

        treeNode1.right = treeNode2;
        treeNode2.right = treeNode3;
        treeNode3.right = treeNode4;

        TreeNode result = balanceBST(treeNode1);

        PrintUtils.printTree(result);
    }
}
