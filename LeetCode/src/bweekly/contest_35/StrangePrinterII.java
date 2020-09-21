package bweekly.contest_35;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 21/9/2020 10:32 AM.
 */
public class StrangePrinterII {

    public boolean isPrintable(int[][] targetGrid) {

        int row = targetGrid.length;
        int col = targetGrid[0].length;
        int[] maxRow = new int[61];
        int[] minRow = new int[61];
        int[] maxCol = new int[61];
        int[] minCol = new int[61];

        Arrays.fill(maxRow, -1);
        Arrays.fill(minRow, 61);
        Arrays.fill(maxCol, -1);
        Arrays.fill(minCol, 61);

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int val = targetGrid[i][j];
                maxRow[val] = Math.max(maxRow[val], i);
                minRow[val] = Math.min(minRow[val], i);
                maxCol[val] = Math.max(maxCol[val], j);
                minCol[val] = Math.min(minCol[val], j);
                set.add(val);
            }
        }

        while (!allZero(targetGrid)) {
            int value = -1;

            for (Integer val : set) {
                if (valid(targetGrid, minRow[val], maxRow[val], minCol[val], maxCol[val], val)) {
                    beZero(targetGrid, minRow[val], maxRow[val], minCol[val], maxCol[val]);
                    value = val;
                    break;
                }
            }

            if (value == -1) {
                return false;
            } else {
                set.remove(value);
            }
        }

        return true;
    }

    public boolean allZero(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean valid(int[][] targetGrid, int minRow, int maxRow, int minCol, int maxCol, int value) {
        for (int i = minRow; i <= maxRow; i++) {
            for (int j = minCol; j <= maxCol; j++) {
                if (targetGrid[i][j] == value || targetGrid[i][j] == 0) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public void beZero(int[][] targetGrid, int minRow, int maxRow, int minCol, int maxCol) {
        for (int i = minRow; i <= maxRow; i++) {
            for (int j = minCol; j <= maxCol; j++) {
                targetGrid[i][j] = 0;
            }
        }
    }

    @Test
    public void test() {
        int[][] arr = {{1, 1, 1, 1}, {1, 2, 2, 1}, {1, 2, 2, 1}, {1, 1, 1, 1}};

        boolean result = isPrintable(arr);

        Assert.assertEquals(true, result);
    }

    @Test
    public void test1() {
        int[][] arr = {{1, 1, 1, 1}, {1, 1, 3, 3}, {1, 1, 3, 4}, {5, 5, 1, 4}};

        boolean result = isPrintable(arr);

        Assert.assertEquals(true, result);
    }

    @Test
    public void test2() {
        int[][] arr = {{1, 2, 1}, {2, 1, 2}, {1, 2, 1}};

        boolean result = isPrintable(arr);

        Assert.assertEquals(false, result);
    }

    @Test
    public void test3() {
        int[][] arr = {{1, 1, 1}, {3, 1, 3}};

        boolean result = isPrintable(arr);

        Assert.assertEquals(false, result);
    }

}
