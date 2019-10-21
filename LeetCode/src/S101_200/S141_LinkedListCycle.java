package S101_200;

import utils.ListNode;

/**
 * https://leetcode.com/problems/linked-list-cycle/
 * Create by haifei on 21/10/2019 11:33 PM.
 */
public class S141_LinkedListCycle {

  public boolean hasCycle(ListNode head) {
    if (null == head) {
      return false;
    }

    ListNode slowNode = head;
    ListNode quickNode = head.next;

    while (quickNode != null) {
      if (slowNode.equals(quickNode)) {
        return true;
      } else {
        slowNode = slowNode.next;
        quickNode = quickNode.next == null ? null : quickNode.next.next;
      }
    }

    return false;
  }
}
