package com.haifei;

import com.haifei.model.ListNode;

/**
 * Create by haifei on 31/7/2018.
 */
public class S16_ReverseList {

  private ListNode reverseList(ListNode node) {
    if (null == node) {
      return node;
    }

    ListNode pre = null;
    ListNode currentNode = node;
    ListNode reverseHeader = null;
    while (currentNode != null) {
      ListNode next = currentNode.next;
      if (next == null) {
        reverseHeader = currentNode;
      }
      currentNode.next = pre;
      pre = currentNode;
      currentNode = next;
    }

    return  reverseHeader;
  }
}
