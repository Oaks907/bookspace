package S001_100;

import S201_300.S206_ReverseLinkedList;
import utils.ListNode;
import utils.PrintUtils;

/**
 * Create by haifei on 12/1/2019 2:40 PM.
 * <p>
 * 交换链表相邻的节点
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 * <p>
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 */
public class S024_SwapNodesinPairs {

  public ListNode swapPairs(ListNode head) {

    if (head == null || head.next == null) {
      return head;
    }

    ListNode node = new ListNode(0);
    node.next = head;

    ListNode pre = node;
    ListNode cur = head;

    while (cur != null && cur.next != null) {

      //交换位置
      ListNode next = cur.next;
      cur.next = next.next;
      next.next = cur;
      pre.next = next;

      //上面节点交换后新的起始位置
      pre = cur;
      cur = cur.next;
    }

    return node.next;
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

    final S024_SwapNodesinPairs swapNodesinPairs = new S024_SwapNodesinPairs();
    final ListNode head = swapNodesinPairs.swapPairs(node1);

    PrintUtils.printLinkedList(head);
  }

}
