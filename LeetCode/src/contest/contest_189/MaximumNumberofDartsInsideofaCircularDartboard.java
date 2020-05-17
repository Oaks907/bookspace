package contest.contest_189;

/**
 * Create by haifei on 17/5/2020 11:23 AM.
 */
public class MaximumNumberofDartsInsideofaCircularDartboard {

    public int numPoints(int[][] points, int r) {
        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;
        int top = Integer.MIN_VALUE;
        int bottom = Integer.MAX_VALUE;

        for (int i = 0; i < points.length; i++) {
            int x = points[i][0];
            int y = points[i][1];
            left = Math.min(x, left );
            right = Math.max(x, right);
            top = Math.max(y, top);
            bottom = Math.min(y, bottom );
        }



    }
}
