package contest.contest_182;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 29/3/2020 10:40 AM.
 */
public class CountNumberofTeams {

    public int numTeams(int[] rating) {

        int result = 0;
        for (int i = 0; i < rating.length; i++) {
            for (int j = i + 1; j < rating.length; j++) {
                for (int k = j + 1; k < rating.length; k++) {
                    if ((rating[i] > rating[j] && rating[j] > rating[k])
                                || (rating[i] < rating[j]&& rating[j] < rating[k])) {
                        result++;
                    }
                }
            }
        }

        return result;
    }

    @Test
    public void test() {
        int[] rating = {2, 5, 3, 4, 1};

        int result = numTeams(rating);

        Assert.assertEquals(3, result);
    }
}
