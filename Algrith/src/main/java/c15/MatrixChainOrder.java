package c15;

import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 1/12/2020 7:04 PM.
 */
public class MatrixChainOrder {

    public int matrixChainOrder(int[] p) {

        int n = p.length - 1;
        int[][] m = new int[p.length][p.length];
        int[][] s = new int[p.length][p.length];

        for (int l = 2; l <= n; l++) {

            for (int i = 1; i <= n - l + 1; i++) {

                int j = i + l - 1;
                m[i][j] = -1;

                for (int k = i; k <= j - 1; k++) {
                    System.out.println("i:" + i + ", j:" + j + ",k:" + k);
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    System.out.print("m[i][k]:"  + m[i][k] + ", m[k + 1][j]:" + m[k + 1][j] + ", q:" + q);
                    if (q < m[i][j] || -1 == m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                    System.out.println("");
                }
                System.out.println("-----");
            }
            System.out.println("。。。。。。。。");
        }

        PrintUtils.printArray(m);
        System.out.println("-----");
        PrintUtils.printArray(s);

        print(s, 1, 6);
        return 0;
    }

    public void print(int[][] s, int i, int j) {

        if (i == j) {
            System.out.print("A");
            System.out.print(i);
        } else {
            System.out.print("(");
            print(s, i, s[i][j]);
            print(s, s[i][j] + 1, j);
            System.out.print(")");
        }
    }

    @Test
    public void test() {
        int[] p = {30, 35, 15, 5, 10, 20, 25};
        matrixChainOrder(p);
    }
}
