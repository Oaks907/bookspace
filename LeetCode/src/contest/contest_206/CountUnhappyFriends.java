package contest.contest_206;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 13/9/2020 11:32 AM.
 */
public class CountUnhappyFriends {

    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {

        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < preferences.length; i++) {
            Map<Integer, Integer> hashMap = map.getOrDefault(i, new HashMap<Integer, Integer>());
            for (int j = 0; j < preferences[0].length; j++) {
                hashMap.put(preferences[i][j], i + j);
            }
            map.put(i, hashMap);
        }

        Map<Integer, Integer> pairMap = new HashMap<>();

        for (int i = 0; i < pairs.length; i++) {

            int x = pairs[i][0];
            int y = pairs[i][1];

            pairMap.put(x, y);
            pairMap.put(y, x);
        }

        int ans = 0;
        for (int i = 0; i < pairs.length; i++) {

            int x = pairs[i][0];
            int y = pairs[i][1];

            ans += happy(pairMap, map, x, y);
            ans += happy(pairMap, map, y, x);
        }

        return ans;
    }

    public int happy(Map<Integer, Integer> pairMap, Map<Integer, Map<Integer, Integer>> map, int x, int y) {
        Map<Integer, Integer> mapX = map.get(x);

        for (Map.Entry<Integer, Integer> entry : mapX.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();

            if (key == y) {
                continue;
            }
            // x存在更好的关系配对z
            if (value < mapX.get(y)) {
                // x与z的关系比z当前关系更好
                // xz的关系值
                Integer xZ = map.get(key).get(x);
                if (xZ < map.get(key).get(pairMap.get(key))) {
                    return 1;
                }
            }
        }
        return 0;
    }

    @Test
    public void test() {
        int[][] pres = {{1, 2, 3}, {3, 2, 0}, {3, 1, 0}, {1, 2, 0}};
        int[][] pairs = {{0, 1}, {2, 3}};

        int result = unhappyFriends(4, pres, pairs);

        Assert.assertEquals(2, result);
    }

    @Test
    public void test2() {
        int[][] pres = {{1, 3, 2}, {2, 3, 0}, {1, 3, 0}, {0, 2, 1}};
        int[][] pairs = {{1, 3}, {0, 2}};

        int result = unhappyFriends(4, pres, pairs);

        Assert.assertEquals(4, result);
    }
}
