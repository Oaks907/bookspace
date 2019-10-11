package S001_100;

import org.junit.Test;
import utils.ListNode;
import utils.PrintUtils;

/**
 * https://leetcode.com/problems/add-two-numbers/
 * Create by haifei on 10/10/2019 9:15 AM.
 */
public class S002_AddTwoNumbers {

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

    int carry = 0;
    int sum = 0;

    ListNode l3 = new ListNode(0);
    ListNode ans = l3;

    while (l1 != null || l2 != null) {
      sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;

      carry = sum / 10;
      sum = sum % 10;

      l3.val = sum;

      if (l1 != null) {
        l1 = l1.next;
      }
      if (l2 != null) {
        l2 = l2.next;
      }

      if (l1 != null || l2 != null || carry > 0) { //这里注意理解
        l3.next = new ListNode(carry);
        l3 = l3.next;
      }
    }

    return ans;
  }

  @Test
  public void test() {

    ListNode l12 = new ListNode(2);
    ListNode l14 = new ListNode(4);
    ListNode l13 = new ListNode(3);

    l12.next = l14;
    l14.next = l13;

    ListNode l25 = new ListNode(5);
    ListNode l26 = new ListNode(6);
    ListNode l24 = new ListNode(4);

    l25.next = l26;
    l26.next = l24;

    ListNode result = addTwoNumbers(l12, l25);

    PrintUtils.printListNode(result);
  }

  @Test
  public void test1() {

    ListNode result = addTwoNumbers(new ListNode(5), new ListNode(5));

    PrintUtils.printListNode(result);
  }

  @Test
  public void test2() {
    ListNode node1_1 = new ListNode(1);
    ListNode node1_8 = new ListNode(8);

    node1_1.next = node1_8;

    ListNode result = addTwoNumbers(node1_1, new ListNode(0));

    PrintUtils.printListNode(result);
  }


}
