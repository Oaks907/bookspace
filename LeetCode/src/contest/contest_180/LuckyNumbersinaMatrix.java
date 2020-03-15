package contest.contest_180;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Create by haifei on 15/3/2020 10:36 AM.
 */
public class LuckyNumbersinaMatrix {

    public List<Integer> luckyNumbers(int[][] matrix) {

        int row = matrix.length;
        int col = matrix[0].length;

        int[] miniRow = new int[row];
        int[] maxCol = new int[col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                miniRow[i] = Math.min(miniRow[i] == 0 ? Integer.MAX_VALUE : miniRow[i], matrix[i][j]);
                maxCol[j] = Math.max(maxCol[j], matrix[i][j]);
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (miniRow[i] == maxCol[j]) {
                    list.add(miniRow[i]);
                }
            }
        }

        return list;
    }

    @Test
    public void test() {

        int[][] matrix = {{3, 7, 8}, {9, 11, 13}, {15, 16, 17}};

        List<Integer> result = luckyNumbers(matrix);

        System.out.println(result);
    }

    @Test
    public void test1() {

        int[][] matrix = {{1, 10, 4, 2}, {9, 3, 8, 7}, {15, 16, 17, 12}};

        List<Integer> result = luckyNumbers(matrix);

        System.out.println(result);
    }

    @Test
    public void test2() {

        int[][] matrix = {{7, 8}, {1, 2}};

        List<Integer> result = luckyNumbers(matrix);

        System.out.println(result);
    }
}
