package com.haifei;

import com.haifei.model.ListNode;

/**
 * Create by haifei on 1/8/2018.
 */
public class S17_MergeSortedLists_loop {

  private ListNode merge(ListNode head1, ListNode head2) {
    if (head1 == null && head2 == null) {
      return null;
    }

    ListNode newNode = null;
    ListNode newHeader = null;
    ListNode node1 = head1;
    ListNode node2 = head2;
    while (node1 != null && node2 != null) {
      if (node1.value < node2.value) {
        if (null == newNode) {
          newNode = node1;
          newHeader = newNode;
        } else {
          newNode.next = node1;
          newNode = newNode.next;
        }
        node1 = node1.next;
      } else {
        if (null == newNode) {
          newNode = node2;
          newHeader = newNode;
        } else {
          newNode.next = node2;
          newNode = newNode.next;
        }
        node2 = node2.next;
      }
      System.out.println("newNode:" + newNode.value);
    }

    if (node1 == null) {
      if (null == newNode) {
        newNode = node2;
        newHeader = newNode;
      } else {
        newNode.next = node2;
      }
    }

    if (node2 == null) {
      if (null == newNode) {
        newNode = node1;
        newHeader = newNode;
      } else {
        newNode.next = node1;
      }
    }

    return newHeader;
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

    ListNode node = new S17_MergeSortedLists_loop().merge(node1, node2);
    while (node != null) {
      System.out.print(node.value + " ");
      node = node.next;
    }
  }
}
