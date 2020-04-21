package challenge;

import org.junit.Assert;
import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 21/4/2020 1:17 AM.
 */
public class NumberofIslands {

    public int numIslands(char[][] grid) {

        if (grid == null || grid.length == 0) {
            return 0;
        }

        int row = grid.length;
        int col = grid[0].length;

        boolean[][] visited = new boolean[row][col];

        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '0' || visited[i][j]) {
                    continue;
                }
                helper(grid, visited, i, j);
                res++;
            }
        }

        PrintUtils.printArray(visited);

        return res;
    }

    private void helper(char[][] grid, boolean[][] visited, int i, int j) {
        int row = grid.length;
        int col = grid[0].length;

        if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] == '0' || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        // left
        helper(grid, visited, i, j - 1);
        // top
        helper(grid, visited, i - 1, j);
        // right
        helper(grid, visited, i, j + 1);
        // bottom
        helper(grid, visited, i + 1, j);
    }

    @Test
    public void test() {
        char[][] grid = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}};

        int result = numIslands(grid);

        Assert.assertEquals(1, result);
    }

    @Test
    public void test1() {
        char[][] grid = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}};

        int result = numIslands(grid);

        Assert.assertEquals(3, result);
    }
}
