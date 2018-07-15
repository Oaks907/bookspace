package com.haifei;

import com.haifei.model.ListNode;

import java.util.Stack;

/**
 * Create by haifei on 24/6/2018.
 */
public class S05_PrintListReversing {

  private static void printListReversing(ListNode head) {
    Stack<Integer> stack = new Stack<Integer>();
    while (head != null) {
      stack.push(head.value);
      head = head.next;
    }

    while (!stack.isEmpty()) {
      System.out.print(stack.pop() + " ");
    }
    System.out.println();
  }

  private static void printListReversing2(ListNode head){
    if (head == null) return;

    printListReversing2(head.next);

    System.out.print(head.value + " ");
  }

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    ListNode next1 = new ListNode(2);
    ListNode next2 = new ListNode(3);
    head.next = next1;
    next1.next = next2;
    printListReversing(head);
    printListReversing2(head);
  }
}
