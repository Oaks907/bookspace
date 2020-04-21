package challenge;

import java.util.List;

/**
 * Create by haifei on 21/4/2020 6:33 PM.
 */
public class LeftmostColumnwithatLeastaOne {

    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {

        List<Integer> dimensions = binaryMatrix.dimensions();
        int row = dimensions.get(0);
        int col = dimensions.get(1);

        int res = -1;
        int x = 0, y = col - 1;

        for (; y >= 0;) {
            if (x < 0 || x >= row || y < 0 || y >= col) {
                break;
            }
            int cur = binaryMatrix.get(x, y);
            if (cur == 0) {
                x++;
            } else if (cur == 1) {
                res = y;
                y--;
            }
        }

        return res;
    }

    interface BinaryMatrix {
        public int get(int x, int y);

        public List<Integer> dimensions();
    }

}