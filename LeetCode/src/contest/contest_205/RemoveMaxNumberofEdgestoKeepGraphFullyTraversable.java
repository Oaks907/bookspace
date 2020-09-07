package contest.contest_205;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 6/9/2020 11:44 AM.
 */
public class RemoveMaxNumberofEdgestoKeepGraphFullyTraversable {

    private boolean[] used;

    /**
     * https://leetcode-cn.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/solution/bing-cha
     * -ji-lu-jing-ya-suo-javaban-ben-hao-li-jie-/
     *
     * @param n
     * @param edges
     * @return
     */
    public int maxNumEdgesToRemove(int n, int[][] edges) {

        used = new boolean[edges.length];
        Arrays.sort(edges, (a, b) -> Integer.compare(b[0], a[0]));

        PrintUtils.printArray(edges);

        if (unionFind(n, edges, 1) != 1) {
            return -1;
        }
        PrintUtils.printArray(used);

        if (unionFind(n, edges, 2) != 1) {
            return -1;
        }

        PrintUtils.printArray(used);

        int result = 0;
        for (boolean u : used) {
            result += u ? 0 : 1;
        }

        return result;
    }

    private int unionFind(int n, int[][] edges, int excludedType) {
        int[] union = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            union[i] = i;
        }

        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];

            if (edge[0] == excludedType) {
                continue;
            }

            int rootA = findRoot(union, edge[1]);
            int rootB = findRoot(union, edge[2]);

            if (rootA != rootB) {
                union[rootA] = rootB;
                used[i] = true;
            }
        }

        PrintUtils.printArray(union);

        int result = 0;
        for (int i = 1; i <= n; i++) {
            if (union[i] == i) {
                result += 1;
            }
        }

        return result;
    }

    private int findRoot(int[] union, int idx) {
        if (union[idx] != idx) {
            int root = findRoot(union, union[idx]);
            union[idx] = root;
            return root;
        }

        return idx;
    }

    @Test
    public void test() {

        int[][] arr = {{3, 1, 2}, {3, 2, 3}, {1, 1, 3}, {1, 2, 4}, {1, 1, 2}, {2, 3, 4}};

        int result = maxNumEdgesToRemove(4, arr);

        Assert.assertEquals(2, result);
    }

    @Test
    public void test1() {

        int[][] arr = {{3, 1, 2}, {3, 2, 3}, {3, 3, 1}, {3, 1, 3}};

        int result = maxNumEdgesToRemove(3, arr);

        System.out.println(result);
    }
}
