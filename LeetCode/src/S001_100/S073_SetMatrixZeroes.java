package S001_100;

import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 26/2/2020 10:27 AM.
 */
public class S073_SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {

        int row = matrix.length;
        int clo = matrix[0].length;
        boolean zRow = false;
        boolean zClo = false;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < clo; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) {
                        zRow = true;
                    }
                    if (j == 0) {
                        zClo = true;
                    }
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < clo; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (zRow) {
            for (int i = 0; i < clo; i++) {
                matrix[0][i] = 0;
            }
        }
        if (zClo) {
            for (int i = 0; i < row; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    @Test
    public void test1() {

        int[][] arr = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};

        setZeroes(arr);

        PrintUtils.printArray(arr);
    }

    @Test
    public void test() {

        int[][] arr = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};

        setZeroes(arr);

        PrintUtils.printArray(arr);
    }
}
