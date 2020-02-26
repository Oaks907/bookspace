package S801_S900;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/friends-of-appropriate-ages/
 * Create by haifei on 26/2/2020 12:12 PM.
 */
public class S825_FriendsOfAppropriateAges {

    public int numFriendRequests(int[] ages) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int age : ages) {
            map.put(age, map.getOrDefault(age, 0) + 1);
        }

        int result = 0;
        for (Integer a : map.keySet()) {
            for (Integer b : map.keySet()) {
                if (request(a, b)) {
                    result += map.get(a) * (map.get(b) - ((a == b) ? 1 : 0));
                }
            }
        }

        return result;
    }

    private boolean request(int a, int b) {
        return !(b <= 0.5 * a + 7 || b > a || (b > 100 && a < 100));
    }
}
