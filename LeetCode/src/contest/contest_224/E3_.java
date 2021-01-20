package contest.contest_224;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by haifei on 17/1/2021 10:37 AM.
 */
public class E3_ {

    public int countGoodRectangles(int[][] rectangles) {
        int maxRect = 0;
        int row = rectangles.length;
        int col = rectangles[0].length;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < row; i++) {
            int curMaxRect = Math.min(rectangles[i][0], rectangles[i][1]);
            if (curMaxRect > maxRect) {
                maxRect = curMaxRect;
                map.put(maxRect, 1);
            } else if ((curMaxRect == maxRect)) {
                Integer orDefault = map.getOrDefault(maxRect, 0);
                map.put(maxRect, orDefault + 1);
            }
        }

        return map.getOrDefault(maxRect, 0);
    }
}
