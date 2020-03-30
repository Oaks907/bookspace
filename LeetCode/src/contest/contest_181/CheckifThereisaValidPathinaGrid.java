package contest.contest_181;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 22/3/2020 10:54 AM.
 */
public class CheckifThereisaValidPathinaGrid {

    int[][][] dirs = {{{0, -1}, {0, 1}}, {{-1, 0}, {1, 0}}, {{0, -1}, {1, 0}}, {{0, 1}, {1, 0}}, {{0, -1}, {-1, 0}},
            {{0, 1}, {-1, 0}}};

    //the idea is you need to check port direction match, you can go to next cell and check whether you can come back.
    public boolean hasValidPath(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0});

        visited[0][0] = true;

        while (!queue.isEmpty()) {

            int[] poll = queue.poll();
            int x = poll[0], y = poll[1];

            int dirIndex = grid[x][y] - 1;

            for (int[] dir : dirs[dirIndex]) {
                int nx = dir[0] + x;
                int ny = dir[1] + y;

                if (nx < 0 || nx >= m || ny < 0 || ny >= n || visited[nx][ny]) {
                    continue;
                }

                //go to the next cell and come back to origin to see if port directions are same
                for (int[] backDir : dirs[grid[nx][ny] - 1]) {

                    if (nx + backDir[0] == x && ny + backDir[1] == y) {
                        visited[nx][ny] = true;
                        queue.add(new int[] {nx, ny});
                    }
                }
            }
        }

        return visited[m - 1][n - 1];
    }

    @Test
    public void test() {

        int[][] arr = {{2, 4, 3}, {6, 5, 2}};

        boolean result = hasValidPath(arr);

        Assert.assertTrue(result);
    }
}
