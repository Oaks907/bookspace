package S301_400;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 3/3/2020 9:23 PM.
 */
public class S392_IsSubsequence {

    public boolean isSubsequence(String s, String t) {

        if (t.length() < s.length()) {
            return false;
        }

        int pre = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            pre = t.indexOf(c, pre);
            if (pre == -1) {
                return false;
            }
            pre++;
        }

        return true;
    }

    @Test
    public void test() {

        boolean result = isSubsequence("abc", "ahbgdc");

        Assert.assertTrue(result);
    }

    @Test
    public void test2() {
        boolean result = isSubsequence("axc", "ahbgdc");

        Assert.assertFalse(result);
    }
}
