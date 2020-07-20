package contest.contest_198;

import java.util.TreeSet;

import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 19/7/2020 10:44 AM.
 */
public class NumberofNodesintheSubTreeWiththeSameLabel {

    private TreeSet<Integer>[] graph;
    private String labels;
    int[] ans;

    public int[] countSubTrees(int n, int[][] edges, String labels) {
        this.labels = labels;
        graph = new TreeSet[n];
        ans = new int[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new TreeSet<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        dfs(0, new boolean[n]);

        return ans;
    }

    private int[] dfs(int root, boolean[] visited) {
        visited[root] = true;
        // 以root为根节点，子树中每个字母的出现的次数
        int[] count = new int[26];
        //先统计根节点
        count[labels.charAt(root) - 'a']++;

        for (int next : graph[root]) {
            if (!visited[next]) {
                int[] res = dfs(next, visited);
                for (int i = 0; i < 26; i++) {
                    count[i] += res[i];
                }
            }
        }
        //更新结果数组
        ans[root] = count[labels.charAt(root)-'a'];
        return count;
    }

    @Test
    public void test5() {
        int[][] arr = {{0, 2}, {0, 3}, {1, 2}};
        int[] result = countSubTrees(4, arr, "aeed");
        //1,1,2,1
        PrintUtils.printArray(result);
    }

    @Test
    public void test() {
        int[][] arr = {{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}};
        int[] result = countSubTrees(7, arr, "abaedcd");
        // 2,1,1,1,1,1,1
        PrintUtils.printArray(result);
    }

    @Test
    public void test1() {
        int[][] arr = {{0, 1}, {1, 2}, {0, 3}};
        int[] result = countSubTrees(4, arr, "bbbb");
        // 4,2,1,1
        PrintUtils.printArray(result);
    }

    @Test
    public void test2() {
        int[][] arr = {{0, 1}, {0, 2}, {1, 3}, {0, 4}};
        int[] result = countSubTrees(5, arr, "aabab");
        // 3,2,1,1,1
        PrintUtils.printArray(result);
    }

    @Test
    public void test3() {
        int[][] arr = {{0, 1}, {0, 2}, {1, 3}, {3, 4}, {4, 5}};
        int[] result = countSubTrees(6, arr, "cbabaa");
        // 1,2,1,1,2,1
        PrintUtils.printArray(result);
    }

}
