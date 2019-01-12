package S201_300;

import utils.ListNode;
import utils.PrintUtils;

/**
 * Create by haifei on 12/1/2019 2:30 PM.
 * 反转链表
 * https://leetcode.com/problems/reverse-linked-list/
 */
public class S206_ReverseLinkedList {

  public ListNode reverseList(ListNode head) {

    if (null == head) {
      return head;
    }

    ListNode pre = null;
    ListNode cur = head;

    while (cur != null) {
      ListNode next = cur.next;
      cur.next = pre;
      pre = cur;
      cur = next;
    }

    return pre;
  }

  public static void main(String[] args) {

    ListNode node1 = new ListNode(1);
    ListNode node2 = new ListNode(2);
    ListNode node3 = new ListNode(3);
    ListNode node4 = new ListNode(4);
    ListNode node5 = new ListNode(5);

    node1.next = node2;
    node2.next = node3;
    node3.next = node4;
    node4.next = node5;

    PrintUtils.printLinkedList(node1);

    final S206_ReverseLinkedList reverseLinkedList = new S206_ReverseLinkedList();
    final ListNode head = reverseLinkedList.reverseList(node1);

    PrintUtils.printLinkedList(head);
  }
}
