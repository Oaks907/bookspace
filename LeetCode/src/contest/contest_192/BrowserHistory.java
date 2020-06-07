package contest.contest_192;

import org.junit.Test;

/**
 * Create by haifei on 7/6/2020 10:50 AM.
 */
public class BrowserHistory {

    ListNode home;
    ListNode cur;

    public BrowserHistory(String homepage) {
        home = new ListNode();
        home.url = homepage;
        cur = home;
    }

    public void visit(String url) {
        ListNode node = new ListNode();
        node.url = url;

        node.pre = cur;
        cur.next = node;
        cur = node;
    }

    public String back(int steps) {
        ListNode pre = cur;
        while (pre != null && steps > 0) {
            steps--;
            cur = pre;
            pre = pre.pre;
        }
        if (null == pre || steps > 0) {
            return cur.url;
        }

        cur = pre;

        return pre.url;
    }

    public String forward(int steps) {
        ListNode next = cur;
        while (next != null && steps > 0) {
            steps--;
            cur = next;
            next = next.next;
        }
        if (next == null || steps > 0) {
            return cur.url;
        }

        cur = next;
        return next.url;
    }

    public class ListNode {
        public String url;
        public ListNode pre;
        public ListNode next;

        public ListNode() {

        }
    }
}
