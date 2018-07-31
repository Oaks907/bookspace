package com.haifei;

import com.haifei.model.ListNode;

/**
 * Create by haifei on 30/7/2018.
 */
public class S13_DeleteNodeInList {
  public ListNode deleteNode(ListNode head, ListNode toBeDeleteNode) {
    if (head == null || toBeDeleteNode == null) {
      return head;
    }

    //删除的是中间节点
    if (toBeDeleteNode.next != null) {
      toBeDeleteNode.value = toBeDeleteNode.next.value;
      toBeDeleteNode.next = toBeDeleteNode.next.next;
    } else if (head == toBeDeleteNode) {
      //删除的是头节点
      head = null;
    } else {
      //删除的是尾节点
      ListNode tempNode = head;
      while (tempNode.next != toBeDeleteNode) {
        tempNode = tempNode.next;
      }
      tempNode.next = null;
    }

    return head;
  }
}
