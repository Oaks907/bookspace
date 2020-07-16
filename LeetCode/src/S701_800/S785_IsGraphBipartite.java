package S701_800;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 16/7/2020 12:52 PM.
 */
public class S785_IsGraphBipartite {

    /**
     * 深度遍历方式
     */
    private final int UNCOLOR = 0;
    private final int RED = 1;
    private final int GREEN = 2;
    int[] color;
    boolean valid = true;

    public boolean isBipartite_dfs(int[][] graph) {
        int n = graph.length;
        color = new int[n];

        Arrays.fill(color, UNCOLOR);

        for (int i = 0; i < graph.length && valid; i++) {
            if (color[i] == UNCOLOR) {
                dfs(graph, RED, i);
            }
        }

        return valid;
    }

    private void dfs(int[][] graph, int colorInfo, int node) {
        color[node] = colorInfo;
        colorInfo = colorInfo == RED ? GREEN : RED;

        for (int i : graph[node]) {
            if (color[i] == UNCOLOR) {
                dfs(graph, colorInfo, i);
                if (!valid) {
                    return;
                }
            } else {
                if (colorInfo != color[i]) {
                    valid = false;
                    return;
                }
            }
        }
    }

    /**
     * 宽度优先搜索
     *
     * @param graph
     * @return
     */
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        color = new int[n];

        Arrays.fill(color, UNCOLOR);

        for (int i = 0; i < graph.length; i++) {
            if (color[i] == UNCOLOR) {
                Queue<Integer> queue = new LinkedList();
                queue.add(i);
                color[i] = RED;

                while (!queue.isEmpty()) {

                    Integer poll = queue.poll();
                    int curColor = color[poll] == RED ? GREEN : RED;

                    for (int neighbor : graph[poll]) {
                        if (color[neighbor] == UNCOLOR) {
                            queue.add(neighbor);
                            color[neighbor] = curColor;
                        } else {
                            if (color[neighbor] != curColor) {
                                PrintUtils.printArray(color);
                                return false;
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    @Test
    public void test() {
        int[][] arr = {{1, 3}, {0, 2}, {1, 3}, {0, 2}};

        boolean result = isBipartite(arr);

        Assert.assertTrue(result);
    }

    @Test
    public void test1() {
        int[][] arr = {{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}};

        boolean result = isBipartite(arr);

        Assert.assertFalse(result);
    }
}
