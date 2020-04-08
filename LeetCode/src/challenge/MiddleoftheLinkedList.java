package challenge;

import utils.ListNode;

/**
 * Create by haifei on 8/4/2020 9:44 PM.
 */
public class MiddleoftheLinkedList {

    public ListNode middleNode(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null) {
            fast = fast.next;
            if (null == fast) {
                break;
            }
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }
}
