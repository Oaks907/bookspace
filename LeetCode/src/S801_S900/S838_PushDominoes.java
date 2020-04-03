package S801_S900;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 1/4/2020 11:09 PM.
 */
public class S838_PushDominoes {

    char L = 'L';
    char R = 'R';
    char D = '.';

    public String pushDominoes(String dominoes) {

        String str = L + dominoes + R;
        StringBuilder res = new StringBuilder();

        for (int i = 0, j = 1; j < str.length(); j++) {

            if (str.charAt(j) == D) {
                continue;
            }

            int length = j - i - 1;

            if (i > 0) {
                res.append(str.charAt(i));
            }

            if (str.charAt(i) == str.charAt(j)) {
                for (int k = 0; k < length; k++) {
                    res.append(str.charAt(i));
                }
            } else if (str.charAt(i) == L && str.charAt(j) == R) {
                for (int k = 0; k < length; k++) {
                    res.append(D);
                }
            } else {
                for (int m = 0; m < length / 2; m++) {
                    res.append(R);
                }

                if (length % 2 == 1) {
                    res.append(D);
                }

                for (int m = 0; m < length / 2; m++) {
                    res.append(L);
                }
            }
            i = j;
        }

        return res.toString();
    }

    @Test
    public void test() {

        String result = pushDominoes(".L.R...LR..L..");

        Assert.assertEquals("LL.RR.LLRRLL..", result);
    }
}
