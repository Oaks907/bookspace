package contest.contest_206;

import org.junit.Assert;
import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 13/9/2020 11:03 AM.
 */
public class SpecialPositionsinaBinaryMatrix {

    public int numSpecial(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;

        int[] rowVal = new int[row];
        int[] colVal = new int[col];

        for (int i = 0; i < row; i++) {
            int val = 0;
            for (int j = 0; j < col; j++) {
                val += mat[i][j];
            }
            rowVal[i] = val;
        }

        for (int i = 0; i < col; i++) {
            int val = 0;
            for (int j = 0; j < row; j++) {
                val += mat[j][i];
            }
            colVal[i] = val;
        }

        int ans = 0;

//        PrintUtils.printArray(rowVal);
//        PrintUtils.printArray(colVal);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (mat[i][j] == 1 && rowVal[i] == 1 && rowVal[i] == colVal[j]) {
                    ans++;
                }
            }
        }

        return ans;
    }

    @Test
    public void test() {
        int[][] arr = {{0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 1, 1}};

        int result = numSpecial(arr);

        Assert.assertEquals(3, result);
    }
}
