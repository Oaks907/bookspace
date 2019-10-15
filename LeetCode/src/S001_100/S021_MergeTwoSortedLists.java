package S001_100;

import org.junit.Test;
import utils.ListNode;
import utils.PrintUtils;

/**
 * Create by haifei on 15/10/2019 9:02 AM.
 */
public class S021_MergeTwoSortedLists {
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

    ListNode node = new ListNode(0);
    ListNode head = node;

    while (l1 != null && l2 != null) {
      if (l1.val > l2.val) {
        node.next = l2;
        l2 = l2.next;
      } else {
        node.next = l1;
        l1 = l1.next;
      }

      node = node.next;
    }

    if (l1 != null) {
      node.next = l1;
    }

    if (l2 != null) {
      node.next = l2;
    }

    return head.next;
  }

  @Test
  public void test() {
    ListNode node1_1 = new ListNode(1);
    ListNode node1_2 = new ListNode(2);
    ListNode node1_4 = new ListNode(4);

    node1_1.next = node1_2;
    node1_2.next = node1_4;

    ListNode node2_1 = new ListNode(2);
    ListNode node2_3 = new ListNode(3);
    ListNode node2_4 = new ListNode(4);

    node2_1.next = node2_3;
    node2_3.next = node2_4;

    ListNode result = mergeTwoLists(node1_1, node2_1);

    PrintUtils.printListNode(result);
  }
}
