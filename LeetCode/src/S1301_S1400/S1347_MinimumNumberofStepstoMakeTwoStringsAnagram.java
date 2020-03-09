package S1301_S1400;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram/
 * Create by haifei on 9/3/2020 1:09 PM.
 */
public class S1347_MinimumNumberofStepstoMakeTwoStringsAnagram {

    /**
     *
     */
    public int minSteps_self(String s, String t) {

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int result = 0;
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (map.containsKey(c) && map.get(c) > 0) {
                map.put(c, map.get(c) - 1);
            } else {
                result++;
            }
        }

        return result;
    }

    public int minSteps(String s, String t) {
        int[] count = new int[26];

        for (int i = 0; i < s.length(); i++) {
            ++count[s.charAt(i) - 'a'];
            --count[t.charAt(i) - 'a'];
        }

        return Arrays.stream(count).map(Math::abs).sum() / 2;
    }

    @Test
    public void test1() {
        int result = minSteps("bab", "aba");

        Assert.assertEquals(1, result);
    }

    @Test
    public void test2() {
        int result = minSteps("leetcode", "practice");

        Assert.assertEquals(5, result);
    }

    @Test
    public void test3() {
        int result = minSteps("anagram", "mangaar");

        Assert.assertEquals(0, result);
    }

    @Test
    public void test4() {
        int result = minSteps("xxyyzz", "xxyyzz");

        Assert.assertEquals(0, result);
    }

    @Test
    public void test5() {
        int result = minSteps("friend", "family");

        Assert.assertEquals(4, result);
    }
}
