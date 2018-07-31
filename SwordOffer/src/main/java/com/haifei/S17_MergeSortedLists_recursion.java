package com.haifei;

import com.haifei.model.ListNode;

/**
 * Create by haifei on 1/8/2018.
 */
public class S17_MergeSortedLists_recursion {

  ListNode merge(ListNode head1, ListNode head2) {
    if (head1 == null) {
      return head2;
    }
    if (head2 == null) {
      return head1;
    }

    ListNode newNodeHead = null;

    if (head1.value < head2.value) {
      newNodeHead = head1;
      newNodeHead.next = merge(head1.next, head2);
    } else {
      newNodeHead = head2;
      newNodeHead.next = merge(head1, head2.next);
    }

    return newNodeHead;
  }

  public static void main(String[] args) {
    ListNode node1 = new ListNode(1);
    ListNode node2 = new ListNode(2);
    ListNode node3 = new ListNode(3);
    ListNode node4 = new ListNode(4);
    ListNode node5 = new ListNode(5);
    ListNode node6 = new ListNode(6);
    ListNode node7 = new ListNode(7);
    ListNode node8 = new ListNode(8);

    node1.next = node3;
    node3.next = node5;
    node5.next = node7;

    node2.next = node4;
    node4.next = node6;
    node6.next = node8;

    ListNode node = new S17_MergeSortedLists_recursion().merge(node1, node2);
    while (node != null) {
      System.out.print(node.value + " ");
      node = node.next;
    }
  }
}
