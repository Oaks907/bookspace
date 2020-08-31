package S101_200;

import org.junit.Test;

import utils.ListNode;
import utils.PrintUtils;

/**
 * https://leetcode.com/problems/sort-list/
 * Create by haifei on 3/1/2020 6:45 PM.
 */
public class S148_SortList {

    public ListNode sortList(ListNode head) {
        return null == head ? null : merge(head);
    }

    public ListNode merge(ListNode node) {
        if (null == node || node.next == null) {
            return node;
        }

        ListNode middleNode = findMiddleNode(node);
        ListNode rightNode = middleNode.next;
        middleNode.next = null;

        ListNode l = merge(node);
        ListNode r = merge(rightNode);
        return merge(l, r);
    }

    public ListNode merge(ListNode left, ListNode right) {
        ListNode temp = new ListNode(0);
        ListNode head = temp;
        while (left != null && right != null) {
            if (left.val >= right.val) {
                temp.next = right;
                right = right.next;
            } else {
                temp.next = left;
                left = left.next;
            }
            temp = temp.next;
        }

        if (left != null) {
            temp.next = left;
        }
        if (right != null) {
            temp.next = right;
        }

        return head.next;
    }

    public ListNode findMiddleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode fast = head.next.next;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    @Test
    public void test() {
        ListNode node1 = new ListNode(1);
        ListNode node2= new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        node1.next = node3;
        node4.next = node2;
        node2.next = node1;

        ListNode listNode = sortList(node4);
        PrintUtils.printListNode(listNode);
    }
}
