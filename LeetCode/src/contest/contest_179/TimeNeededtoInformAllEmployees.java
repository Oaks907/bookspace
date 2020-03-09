package contest.contest_179;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 8/3/2020 11:35 AM.
 */
public class TimeNeededtoInformAllEmployees {

    /*
     * 自己写的，先建立树， 在深度优先遍历，太复杂了
     */
    public int numOfMinutes_bad(int n, int headID, int[] manager, int[] informTime) {
        if (manager == null || 0 == manager.length) {
            return 0;
        }

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < manager.length; i++) {
            int key = manager[i];
            if (key == -1) {
                continue;
            }
            List<Integer> list = map.getOrDefault(key, new ArrayList<>());
            list.add(i);
            map.put(key, list);
        }

        Node head = new Node();
        head.val = headID;

        head = buildTree(head, map);

        dfs(head, 0, informTime);

        return max;
    }

    public Node buildTree(Node head, Map<Integer, List<Integer>> map) {
        if (!map.containsKey(head.val)) {
            return head;
        }

        List<Integer> list = map.get(head.val);

        for (int i = 0; i < list.size(); i++) {
            Node node = new Node();
            node.val = list.get(i);

            head.children.add(node);
            buildTree(node, map);
        }

        return head;
    }

    int max = Integer.MIN_VALUE;

    public void dfs(Node node, int sum, int[] inform) {
        List<Node> children = node.children;

        if (children.isEmpty()) {
            max = Math.max(sum, max);
            return;
        }

        sum += inform[node.val];

        for (int i = 0; i < children.size(); i++) {
            Node childNode = children.get(i);
            dfs(childNode, sum, inform);
        }
    }

    public class Node {
        public int val;
        public List<Node> children = new ArrayList<>();

        public Node() {
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    /*
     * 解法二，
     * https://leetcode.com/problems/time-needed-to-inform-all-employees/discuss/532496/Java-BFS-and-store-time-for
     * -each-node
     */
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < manager.length; i++) {
            if (manager[i] == -1) {
                continue;
            }
            if (!map.containsKey(manager[i])) {
                map.put(manager[i], new ArrayList<>());
            }
            map.get(manager[i]).add(i);
        }
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(headID, 0));

        while (q.size() > 0) {

            Pair poll = q.poll();
            ans = Math.max(ans, poll.time);

            if (informTime[poll.node] != 0) {
                List<Integer> children = map.get(poll.node);
                for (int i = 0; i < children.size(); i++) {
                    q.add(new Pair(children.get(i), poll.time + informTime[poll.node]));
                }
            }
        }

        return ans;
    }

    class Pair {
        int node;
        int time;

        Pair(int node, int time) {
            this.node = node;
            this.time = time;
        }
    }

    @Test
    public void test6() {
        int[] manger = {5, 9, 6, 10, -1, 8, 9, 1, 9, 3, 4};
        int[] inform = {0, 213, 0, 253, 686, 170, 975, 0, 261, 309, 337};
        int result = numOfMinutes(11, 4, manger, inform);

        Assert.assertEquals(2560, result);
    }

    @Test
    public void test() {
        int[] manger = {-1};
        int[] inform = {0};
        int result = numOfMinutes(1, 0, manger, inform);

        Assert.assertEquals(0, result);
    }

    @Test
    public void test2() {
        int[] manger = {2, 2, -1, 2, 2, 2};
        int[] inform = {0, 0, 1, 0, 0, 0};
        int result = numOfMinutes(6, 2, manger, inform);

        Assert.assertEquals(1, result);
    }

    @Test
    public void test3() {
        int[] manger = {1, 2, 3, 4, 5, 6, -1};
        int[] inform = {0, 6, 5, 4, 3, 2, 1};
        int result = numOfMinutes(7, 6, manger, inform);

        Assert.assertEquals(21, result);
    }

    @Test
    public void test4() {
        int[] manger = {-1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6};
        int[] inform = {1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0};
        int result = numOfMinutes(15, 0, manger, inform);

        Assert.assertEquals(3, result);
    }

    @Test
    public void test5() {
        int[] manger = {3, 3, -1, 2};
        int[] inform = {0, 0, 162, 914};
        int result = numOfMinutes(4, 2, manger, inform);

        Assert.assertEquals(1076, result);
    }

}
