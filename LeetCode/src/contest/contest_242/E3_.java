package contest.contest_242;

import org.junit.Test;

/**
 * Create by haifei on 23/5/2021 10:26 AM.
 */
public class E3_ {

    public boolean checkZeroOnes(String s) {

        if (null == s || s.length() == 0) {
            return false;
        }

        char[] c = s.toCharArray();

        int maxZero = 0;
        int maxOne = 0;
        int countZero = 0;
        int countOne = 0;
        char pre = c[0];
        boolean preIsZero;
        if (pre == '1') {
            countOne++;
            maxOne++;
            preIsZero = false;
        } else {
            maxZero++;
            preIsZero = true;
            countZero++;
        }

        for (int i = 1; i < c.length; i++) {
            char cur = c[i];
            if (preIsZero && cur == '0') {
                countZero++;
                maxZero = Math.max(maxZero, countZero);
            } else if (!preIsZero && cur == '1') {
                countOne++;
                maxOne = Math.max(maxOne, countOne);
            } else {
                countOne = 0;
                countZero = 0;
                if (cur == '0') {
                    countZero = 1;
                } else {
                    countOne = 1;
                }
                preIsZero = !preIsZero;
            }
        }

        return maxOne > maxZero;
    }

    @Test
    public void test() {
        System.out.println(checkZeroOnes("111000"));
    }
}
