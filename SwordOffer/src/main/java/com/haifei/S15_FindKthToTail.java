package com.haifei;

import com.haifei.model.ListNode;

/**
 * Create by haifei on 31/7/2018.
 * 查找倒数第k个节点
 */
public class S15_FindKthToTail {

  private ListNode findKthToTail(ListNode head, int k) {
    if (head == null) {
      return head;
    }
    ListNode currentNode = head;
    for (int i = 0; i < k - 1; i++) {
      if (currentNode != null) {
        currentNode = currentNode.next;
      }
      if (currentNode == null) {
        return null;
      }
    }
    ListNode slowNode = head;
    while (currentNode != null) {
      currentNode = currentNode.next;
      slowNode = slowNode.next;
    }

    return slowNode;
  }
}
