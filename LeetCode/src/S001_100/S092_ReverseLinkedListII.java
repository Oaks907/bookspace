package S001_100;

import org.junit.Test;
import utils.ListNode;
import utils.PrintUtils;

/**
 * Create by haifei on 26/1/2020 3:20 PM.
 */
public class S092_ReverseLinkedListII {

  public ListNode reverseBetween(ListNode head, int m, int n) {

    if (head == null) {
      return null;
    }
    ListNode dummy = new ListNode(0); // create a dummy node to mark the head of this list
    dummy.next = head;
    ListNode pre = dummy; // make a pointer pre as a marker for the node before reversing
    for (int i = 0; i < m - 1; i++) {
      pre = pre.next;
    }

    ListNode start = pre.next; // a pointer to the beginning of a sub-list that will be reversed
    ListNode then = start.next; // a pointer to a node that will be reversed

    // 1 - 2 -3 - 4 - 5 ; m=2; n =4 ---> pre = 1, start = 2, then = 3
    // dummy-> 1 -> 2 -> 3 -> 4 -> 5
    for (int i = 0; i < n - m; i++) {
      start.next = then.next;
      then.next = pre.next;
      pre.next = then;
      then = start.next;
    }

    // first reversing : dummy->1 - 3 - 2 - 4 - 5; pre = 1, start = 2, then = 4
    // second reversing: dummy->1 - 4 - 3 - 2 - 5; pre = 1, start = 2, then = 5 (finish)
    return dummy.next;
  }

  @Test
  public void test() {
    ListNode node1 = new ListNode(1);
    ListNode node2 = new ListNode(2);
    ListNode node3 = new ListNode(3);
    ListNode node4 = new ListNode(4);
    ListNode node5 = new ListNode(5);
    node1.next = node2;
    node2.next = node3;
    node3.next = node4;
    node4.next = node5;

    PrintUtils.printListNode(node1);

    ListNode listNode = reverseBetween(node1, 2, 4);

    PrintUtils.printListNode(node1);
  }

  @Test
  public void test2() {
    ListNode node1 = new ListNode(1);
    ListNode node2 = new ListNode(2);
    ListNode node3 = new ListNode(3);
    ListNode node4 = new ListNode(4);
    ListNode node5 = new ListNode(5);
    node1.next = node2;
    node2.next = node3;
    node3.next = node4;
    node4.next = node5;

    PrintUtils.printListNode(node1);

    ListNode listNode = reverseBetween(node1, 1, 4);

    PrintUtils.printListNode(node1);
  }
}
