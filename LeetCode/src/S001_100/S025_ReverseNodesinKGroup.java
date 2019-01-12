package S001_100;

import utils.ListNode;
import utils.PrintUtils;

import java.util.Stack;

/**
 * Create by haifei on 12/1/2019 3:35 PM.
 *
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 */
public class S025_ReverseNodesinKGroup {

  public ListNode reverseKGroup(ListNode head, int k) {

    Stack<ListNode> stack = new Stack<>();

    ListNode root = new ListNode(0);
    root.next = head;

    ListNode pre = root;
    ListNode cur = head;

    while (cur != null) {

      while (cur != null && stack.size() < k) {
        stack.push(cur);
        cur = cur.next;
      }

      if (cur == null && stack.size() != k) {
        return root.next;
      }

      ListNode next = stack.peek().next;

      while (!stack.isEmpty()) {

        ListNode node = stack.pop();
        pre.next = node;
        pre = node;
      }

      cur = next;
      pre.next = cur;
    }

    return root.next;
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

    final S025_ReverseNodesinKGroup reverseNodesinKGroup = new S025_ReverseNodesinKGroup();
    final ListNode head = reverseNodesinKGroup.reverseKGroup(node1, 3);

    PrintUtils.printLinkedList(head);
  }
}
