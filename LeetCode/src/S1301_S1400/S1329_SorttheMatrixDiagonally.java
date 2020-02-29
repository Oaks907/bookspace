package S1301_S1400;

import java.util.HashMap;
import java.util.PriorityQueue;

import org.junit.Test;

import utils.PrintUtils;

/**
 * https://leetcode.com/problems/sort-the-matrix-diagonally/
 * Create by haifei on 29/2/2020 10:58 PM.
 */
public class S1329_SorttheMatrixDiagonally {

    public int[][] diagonalSort(int[][] mat) {

        int row = mat.length;
        int col = mat[0].length;

        HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                map.putIfAbsent(i - j, new PriorityQueue<>());
                map.get(i - j).add(mat[i][j]);
            }
        }

        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                mat[i][j] = map.get(i - j).poll();
            }
        }

        return mat;
    }

    @Test
    public void test() {
        int[][] arr = {{3, 3, 1, 1}, {2, 2, 1, 2}, {1, 1, 1, 2}};

        int[][] result = diagonalSort(arr);

        PrintUtils.printArray(result);
    }
}
