package com.haifei;

import com.haifei.model.ComplexListNode;
import com.haifei.model.ListNode;

/**
 * Create by haifei on 3/8/2018.
 */
public class S26_CopyComplexList {

  private ComplexListNode copyComplexList(ComplexListNode head) {
    if (head == null) {
      return null;
    }

    copyNode(head);
    printList(head);
    copySibling(head);
    printList(head);
    ComplexListNode resultHead = splitList(head);
    printList(resultHead);
    return resultHead;
  }

  //第一步:在每个Node后面复制一个Node'
  private void copyNode(ComplexListNode head) {
    ComplexListNode currentNode = head;
    while (currentNode != null) {
      ComplexListNode node = new ComplexListNode(currentNode.value);
      ComplexListNode next = currentNode.next;
      currentNode.next = node;
      node.next = next;
      currentNode = next;
    }
  }

  //第二步:复制Node的随机指针
  private void copySibling(ComplexListNode head) {
    ComplexListNode currentNode = head;
    while (currentNode != null) {
      ComplexListNode currentSiblingNode = currentNode.sibling;
      if (currentSiblingNode == null) {
        currentNode = currentNode.next.next;
        continue;
      }
      ComplexListNode copyNode = currentNode.next;
      copyNode.sibling = currentSiblingNode.next;
      currentNode = currentNode.next.next;
    }
  }

  //第三步：将链表分割为两个
  private ComplexListNode splitList(ComplexListNode head) {
    ComplexListNode currentNode = head;
    ComplexListNode resultNode = head.next;
    while (currentNode != null) {
      ComplexListNode copyNode = currentNode.next;
      currentNode.next = copyNode.next;
      currentNode = copyNode.next;
      if (currentNode == null) {
        break;
      }
      copyNode.next = currentNode.next;
    }
    return resultNode;
  }

  private void printList(ComplexListNode head) {
    System.out.println();
    System.out.print("order  :");
    ComplexListNode currentNode = head;
    while (currentNode != null) {
      System.out.print(currentNode.value + " ");
      currentNode = currentNode.next;
    }
    System.out.println();
    System.out.print("sibling:");
    currentNode = head;
    while (currentNode != null) {
      if (null != currentNode.sibling) {
        System.out.print(currentNode.sibling.value + " ");
      } else {
        System.out.print( "* ");
      }
      currentNode = currentNode.next;
    }
  }

  private void printOrder(ComplexListNode head) {
    System.out.print("order:");
    ComplexListNode currentNode = head;
  }

  public static void main(String[] args) {
    ComplexListNode node1 = new ComplexListNode(1);
    ComplexListNode node2 = new ComplexListNode(2);
    ComplexListNode node3 = new ComplexListNode(3);
    ComplexListNode node4 = new ComplexListNode(4);
    ComplexListNode node5 = new ComplexListNode(5);

    node1.next = node2;
    node2.next = node3;
    node3.next = node4;
    node4.next = node5;

    node1.sibling = node3;
    node2.sibling = node5;
    node4.sibling = node2;

    S26_CopyComplexList s26_copyComplexList = new S26_CopyComplexList();
    s26_copyComplexList.copyComplexList(node1);
  }
}
