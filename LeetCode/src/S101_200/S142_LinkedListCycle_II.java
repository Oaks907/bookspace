package S101_200;

import utils.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by haifei on 12/1/2019 3:00 PM.
 *
 * https://leetcode.com/problems/linked-list-cycle-ii/
 *
 * 返回链表中出现环的位置，不存在返回 -1
 */
public class S142_LinkedListCycle_II {

  public ListNode detectCycle(ListNode head) {

    ListNode slow = head;
    ListNode quick = head;
    while (quick != null && quick.next != null) {

      slow = slow.next;
      quick = quick.next.next;

      //存在环就已经相等了
      if (slow == quick) {

        ListNode slow2 = head;

        while (slow2 != slow) {
          slow = slow.next;
          slow2 = slow2.next;
        }

        return slow;
      }
    }
    return null;
  }

  /**
   * 使用 map
   * @param head
   * @return
   */
  public ListNode detectCycle_2(ListNode head) {
    Map<ListNode, ListNode> map = new HashMap<>();

    while (head != null) {

      if (map.containsKey(head)) {
        return map.get(head);
      }

      map.put(head, head);
      head = head.next;
    }

    return null;
  }
}
