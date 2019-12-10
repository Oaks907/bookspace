package S1201_S1300;

import org.junit.Assert;
import org.junit.Test;
import utils.PrintUtils;

/**
 * Create by haifei on 10/12/2019 11:24 PM.
 */
public class S1267_CountServersthatCommunicate {

  public int countServers(int[][] grid) {
    if (grid == null || grid.length == 0) return 0;

    int totalServers = 0;

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == 1) {
          int server = search(grid, i, j);
          if (server > 1) {
            totalServers += server;
          }
        }
      }
    }

    return totalServers;
  }

  private int search(int[][] grid, int row, int col) {
    if (row < 0 || row >= grid.length) return 0;
    if (col < 0 || col >= grid[row].length) return 0;
    if (grid[row][col] == 0) return 0;

    int servers = 1;
    grid[row][col] = 0;

    for (int i = 0; i < grid[row].length; i++) {
      if (grid[row][i] == 1) {
        servers += search(grid, row, i);
      }
    }

    for (int i = 0; i < grid.length; i++) {
      if (grid[i][col] == 1) {
        servers += search(grid, i, col);
      }
    }

    return servers;
  }

  @Test
  public void test() {
    int[][] grid = {{1, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};

    int result = countServers(grid);

    Assert.assertEquals(4, result);
  }

}
