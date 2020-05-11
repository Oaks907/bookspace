package challenge.may;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 11/5/2020 12:50 PM.
 */
public class FindtheTownJudge {

    public int findJudge(int N, int[][] trust) {

        // 被人信任
        int[] hadPeopleTrust = new int[N + 1];
        // 是否相信别人
        int[] trustPeople = new int[N + 1];

        for (int[] peo : trust) {
            int a = peo[0];
            int b = peo[1];
            hadPeopleTrust[b]++;
            trustPeople[a]++;
        }

        for (int i = 1; i <= N; i++) {
            if (hadPeopleTrust[i] == N - 1 && trustPeople[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        int[][] arr = {{1, 3}, {2, 3}};

        int result = findJudge(3, arr);

        Assert.assertEquals(3, result);
    }
}
