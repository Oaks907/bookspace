package contest.contest_204;

import java.util.Arrays;

/**
 * Create by haifei on 31/8/2020 12:33 AM.
 */
public class MinimumNumberofDaystoDisconnectIsland {

    int N = 40;
    boolean[][] vis = new boolean[N][N];
    int[][] g;
    int row, col;
    int[] dx = {0, -1, 0, 1};
    int[] dy = {-1, 0, 1, 0};

    public int minDays(int[][] grid) {
        row = grid.length;
        col = grid[0].length;
        g = grid;

        if (check()) {
            return 0;
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (g[i][j] == 1) {
                    g[i][j] = 0;
                    if (check()) {
                        return 1;
                    }
                    g[i][j] = 1;
                }
            }
        }
        return 2;
    }

    private boolean check() {

        for (int i = 0; i < row; i++) {
            Arrays.fill(vis[i], false);
        }

        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!vis[i][j] && g[i][j] == 1) {
                    dfs(i, j);
                    count++;
                }
            }
        }
        return count > 1;
    }

    /**
     * 由x，y向周围搜索
     *
     * @param x
     * @param y
     */
    private void dfs(int x, int y) {
        if (vis[x][y]) {
            return;
        }
        vis[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int a = x + dx[i];
            int b = y + dy[i];
            if (a < 0 || a >= row || b < 0 || b >= col) {
                continue;
            }
            if (vis[a][b]) {
                continue;
            }
            if (g[a][b] == 0) {
                continue;
            }
            dfs(a, b);
        }
    }
}
