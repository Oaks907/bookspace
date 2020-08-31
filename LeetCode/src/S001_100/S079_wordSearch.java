package S001_100;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 3/1/2020 9:45 PM.
 */
public class S079_wordSearch {

    int[][] dp;
    
    public boolean exist(char[][] board, String word) {

        int row = board.length;
        int col = board[0].length;

        dp = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (word.charAt(0) == board[i][j]) {
                    if (helper(board, i, j, word, 0)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean helper(char[][] board, int x, int y, String word, int index) {

        // 字符串查找完毕
        if (index == word.length()) {
            return true;
        }

        if (x < 0 || x >= board.length) {
            return false;
        }
        if (y < 0 || y >= board[0].length) {
            return false;
        }

        if (word.charAt(index) != board[x][y]) {
            return false;
        }

        if (dp[x][y] == 1) {
            return false;
        } else {
            dp[x][y] = 1;
        }

        boolean top = helper(board, x - 1, y, word, index + 1);
        boolean bottom = helper(board, x + 1, y, word, index + 1);
        boolean left = helper(board, x, y - 1, word, index + 1);
        boolean right = helper(board, x, y + 1, word, index + 1);

        if (top || bottom || left || right) {
            return true;
        }

        dp[x][y] = 0;

        return false;
    }

    @Test
    public void test4() {
        char[][] arr = {{'a', 'b'}, {'c', 'd'}};

        boolean result = exist(arr, "cdba");

        Assert.assertTrue(result);
    }

    @Test
    public void test() {
        char[][] arr = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};

        boolean result = exist(arr, "ABCCED");

        Assert.assertTrue(result);
    }

    @Test
    public void test1() {
        char[][] arr = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};

        boolean result = exist(arr, "SEE");

        Assert.assertTrue(result);
    }

    @Test
    public void test2() {
        char[][] arr = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};

        boolean result = exist(arr, "ABCB");

        Assert.assertFalse(result);
    }

}
