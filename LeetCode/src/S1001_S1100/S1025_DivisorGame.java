package S1001_S1100;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 2/3/2020 11:20 PM.
 */
public class S1025_DivisorGame {

    public boolean divisorGame(int N) {
        return N % 2 == 0;
    }

    @Test
    public void test() {

        boolean result = divisorGame(2);

        Assert.assertTrue(result);
    }

    @Test
    public void test1() {

        boolean result = divisorGame(3);

        Assert.assertFalse(result);
    }
}
