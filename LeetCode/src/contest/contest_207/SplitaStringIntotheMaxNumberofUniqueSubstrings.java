package contest.contest_207;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 20/9/2020 11:01 AM.
 */
public class SplitaStringIntotheMaxNumberofUniqueSubstrings {

    Set<String> set = new HashSet<>();
    int ans = 0;

    public int maxUniqueSplit(String s) {

        help(s, 0, 0, 1);

        return ans;
    }

    private void help(String s, int start, int end, int res) {
        if (end == s.length() - 1) {
            String str = s.substring(start, end + 1);
            if (set.contains(str)) {
                return;
            } else {
                ans = Math.max(ans, res);
                return;
            }
        }

        String cur = s.substring(start, end + 1);

        if (!set.contains(cur)) {
            set.add(cur);
            help(s, end + 1, end + 1, res + 1);
            set.remove(cur);
        }

        help(s, start, end + 1, res);
    }

    @Test
    public void test() {

        int result = maxUniqueSplit("ababccc");

        Assert.assertEquals(5, result);
    }

    @Test
    public void test1() {

        int result = maxUniqueSplit("aba");

        Assert.assertEquals(2, result);
    }

    @Test
    public void test2() {

        int result = maxUniqueSplit("aa");

        Assert.assertEquals(1, result);
    }
}
