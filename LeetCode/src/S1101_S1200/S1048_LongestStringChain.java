package S1101_S1200;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 12/3/2020 1:18 PM.
 */
public class S1048_LongestStringChain {

    /**
     * 自己写的DP数据求解
     *
     * @param words
     * @return
     */
    public int longestStrChain_self(String[] words) {

        // sort by string length
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int length = words.length;
        int[] dp = new int[length];
        dp[length - 1] = 1; //default
        int result = 1;

        for (int i = length - 2; i >= 0; i--) {

            String curStr = words[i + 1];

            for (int j = i; j >= 0; j--) {

                String str = words[j];
                dp[j] = Math.max(dp[j], 1);

                // same length
                if (curStr.length() == str.length()) {
                    continue;
                }

                if (curStr.length() != str.length() + 1) {
                    break;
                }

                // str is a predecessor of curStr
                if (isPredecessor(curStr, str)) {
                    dp[j] = Math.max(dp[j], dp[i + 1] + 1);
                }

                result = Math.max(dp[j], result);

            }
        }

        return result;
    }

    private boolean isPredecessor(String source, String str) {
        int sourceIndex = 0;
        int strIndex = 0;
        int count = 0;
        while (strIndex < str.length()) {
            if (count > 1) {
                return false;
            }
            if (str.charAt(strIndex) != source.charAt(sourceIndex + count)) {
                count++;
            } else {
                strIndex++;
                sourceIndex++;
            }
        }

        return true;
    }

    /**
     * 使用Hashmap求解
     *
     * @param words
     * @return
     */
    public int longestStrChain(String[] words) {

        Map<String, Integer> map = new HashMap<>();
        Arrays.sort(words, Comparator.comparingInt(String::length));

        int res = 0;
        for (String word : words) {

            int best = 0;
            for (int i = 0; i < word.length(); i++) {
                String prev = word.substring(0, i) + word.substring(i + 1);
                best = Math.max(best, map.getOrDefault(prev, 0) + 1);
            }
            map.put(word, best);
            res = Math.max(res, best);
        }

        return res;
    }

    @Test
    public void test2() {
        String[] str = {"sgtnz", "sgtz", "sgz", "ikrcyoglz", "ajelpkpx", "ajelpkpxm", "srqgtnz", "srqgotnz", "srgtnz",
                "ijkrcyoglz"};

        int result = longestStrChain(str);

        System.out.println(isPredecessor("ijkrcyoglz", "ikrcyoglz"));

        Assert.assertEquals(6, result);
    }

    @Test
    public void test1() {
        String[] str =
                {"ksqvsyq", "ks", "kss", "czvh", "zczpzvdhx", "zczpzvh", "zczpzvhx", "zcpzvh", "zczvh", "gr", "grukmj",
                        "ksqvsq", "gruj", "kssq", "ksqsq", "grukkmj", "grukj", "zczpzfvdhx", "gru"};

        int result = longestStrChain(str);

        Assert.assertEquals(7, result);
    }

    @Test
    public void test() {
        String[] str = {"a", "b", "ba", "bca", "bda", "bdca"};

        int result = longestStrChain(str);

        Assert.assertEquals(4, result);
    }
}
