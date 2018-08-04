package com.haifei.model;

/**
 * Create by haifei on 3/8/2018.
 */
public class ComplexListNode {
  public int value;
  public ComplexListNode next;
  public ComplexListNode sibling;

  public ComplexListNode(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public ComplexListNode getNext() {
    return next;
  }

  public void setNext(ComplexListNode next) {
    this.next = next;
  }

  public ComplexListNode getSibling() {
    return sibling;
  }

  public void setSibling(ComplexListNode sibling) {
    this.sibling = sibling;
  }
}
