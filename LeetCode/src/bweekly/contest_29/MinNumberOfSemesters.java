package bweekly.contest_29;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 27/6/2020 11:01 PM.
 */
public class MinNumberOfSemesters {

    public int minNumberOfSemesters(int n, int[][] dependencies, int k) {

        int row = dependencies.length;
        int col = dependencies[0].length;

        Map<Integer, TempNode> map = new HashMap<>();
        for (int i = 0; i < row; i++) {
            TempNode node = new TempNode();
            node.val = dependencies[i][0];
            map.put(node.val, node);

            if (!map.containsKey(dependencies[i][1])) {
                TempNode parent = new TempNode();
                parent.val = dependencies[i][1];
                map.put(parent.val, parent);
            }
        }

        for (int i = 0; i < row; i++) {
            TempNode cur = map.get(dependencies[i][0]);
            TempNode parent = map.get(dependencies[i][1]);

            cur.parent = parent;
            parent.childNode.add(cur);
        }

        TempNode head = null;
        for (TempNode node : map.values()) {
            if (node.parent == null) {
                head = node;
            }
        }
        List<TempNode> list = new ArrayList<>();
        houxu(head, list);

        System.out.println(map);
        System.out.println(list);

        return 0;
    }

    public void getCount(List<TempNode> list, int k) {

        int count = 0;
        int curIndex = 0;
        for (int i = 0; i < list.size(); i += k) {
            curIndex = i;
            Set<Integer> set = new HashSet<>();
            for (int j = k - 1; j >= 0; j--) {
                TempNode node = list.get(curIndex + j);
                set.add(node.val);
                set.add(node.parent.val);
            }

        }

        return 0;
    }

    public void houxu(TempNode node, List<TempNode> list) {
        if (node == null) {
            return;
        }

        for (TempNode child : node.childNode) {
            houxu(child, list);
        }

        list.add(node);
    }

    public class TempNode {
        int val;
        TempNode parent;
        List<TempNode> childNode = new ArrayList<>();

        @Override
        public String toString() {

            List<Integer> list = new ArrayList<>();
            for (TempNode node : childNode) {
                list.add(node.val);
            }
            return "TempNode{" + "val=" + val + ", parent=" + (parent != null ? parent.val : "null") + ",child = "
                           + list + '}';
        }
    }

    @Test
    public void test() {
        int[][] arr = {{2, 1}, {3, 1}, {1, 4}};

        int result = minNumberOfSemesters(4, arr, 2);

        Assert.assertEquals(3, result);
    }

    @Test
    public void test1() {
        int[][] arr = {{2, 1}, {3, 1}, {4, 1}, {1, 5}};

        int result = minNumberOfSemesters(5, arr, 2);

        Assert.assertEquals(4, result);
    }
}
