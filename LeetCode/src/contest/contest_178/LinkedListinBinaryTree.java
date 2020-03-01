package contest.contest_178;

import org.junit.Test;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import model.TreeNode;
import utils.ListNode;

/**
 * Create by haifei on 1/3/2020 10:59 AM.
 */
public class LinkedListinBinaryTree {

    // Time Limit exceeded
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }

        return helper(head, head, root, root);
    }

    private boolean helper(ListNode head, ListNode cur, TreeNode startNode, TreeNode curTreeNode) {
        if (cur == null) {
            return true;
        }
        if (curTreeNode == null) {
            return false;
        }

        boolean left = false;
        boolean right = false;
        if (cur.val == curTreeNode.val) {
            left = helper(head, cur.next, startNode, curTreeNode.left);
            right = helper(head, cur.next, startNode, curTreeNode.right);
            return left || right;
        } else {
            left = helper(head, head, startNode.left, startNode.left);
            right = helper(head, head, startNode.right, startNode.right);
            return left || right;
        }
    }

    // solution
    public boolean isSubPath2(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }

        String str = "";
        while (head != null) {
            str += head.val;
            head = head.next;
        }

        return helper(root, "", str);
    }

    private boolean helper(TreeNode node, String cur, String str) {
        if (node == null) {
            if (cur.contains(str)) {
                return true;
            }
            return false;
        }

        return helper(node.left, cur + node.val, str) || helper(node.right, cur + node.val, str);
    }

    // GOOD soution
    public boolean isSubPath3(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }

        return check(root, head) || isSubPath3(head, root.left) || isSubPath3(head, root.right);
    }

    public boolean check(TreeNode root, ListNode head) {
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        if (root.val != head.val) {
            return false;
        }

        return check(root.left, head.next) || check(root.right, head.next);
    }

    @Test
    public void test() {
        ListNode node4 = new ListNode(4);
        ListNode node2 = new ListNode(2);
        ListNode node8 = new ListNode(8);

        node4.next = node2;
        node2.next = node8;

        TreeNode tree1 = new TreeNode(1);
        TreeNode tree2 = new TreeNode(2);
        TreeNode tree3 = new TreeNode(3);
        TreeNode tree4 = new TreeNode(4);
        TreeNode tree8 = new TreeNode(8);
        TreeNode tree6 = new TreeNode(6);

        tree1.left = tree3;
        tree1.right = tree4;

        tree4.left = tree2;

        tree2.right = tree8;

        //        System.out.println(isSubPath(node4, tree1));
        System.out.println(isSubPath2(node4, tree1));
    }
}
