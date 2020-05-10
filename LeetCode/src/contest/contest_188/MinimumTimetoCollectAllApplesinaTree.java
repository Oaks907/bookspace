package contest.contest_188;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * Create by haifei on 10/5/2020 11:29 AM.
 */
public class MinimumTimetoCollectAllApplesinaTree {

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {

        Map<Integer, List<Integer>> map = new HashMap<>();
        buildTree(edges, map);

        return helper(0, hasApple, map);
    }

    private int helper(int node, List<Boolean> hasApple, Map<Integer, List<Integer>> map) {

        int res = 0;

        for (Integer child : map.getOrDefault(node, new LinkedList<>())) {
            res += helper(child, hasApple, map);
        }

        if ((hasApple.get(node) || res > 0) && node != 0) {
            res += 2;
        }

        return res;
    }

    private void buildTree(int[][] edges, Map<Integer, List<Integer>> map) {

        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];

            map.putIfAbsent(a, new LinkedList<>());
            map.get(a).add(b);
        }
    }

    @Test
    public void test() {

        int[][] arr = {{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}};
        List<Boolean> list = new ArrayList<>();
        list.add(false);
        list.add(false);
        list.add(true);
        list.add(false);
        list.add(true);
        list.add(true);
        list.add(false);

        minTime(7, arr, list);
    }

}
