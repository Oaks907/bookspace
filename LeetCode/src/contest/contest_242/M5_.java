package contest.contest_242;

/**
 * Create by haifei on 23/5/2021 10:45 AM.
 */
public class M5_ {

    public boolean canReach(String s, int minJump, int maxJump) {
        char[] c = s.toCharArray();
        int len = c.length;

        int[] dp = new int[len];
        dp[0] = 1;
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '1') {
                dp[i] = -1;
            }
        }

        int statLen = 0;
        for (int i = 0; i < c.length; i++) {

            if (dp[i] != 1) {
                continue;
            }

            int start = i + minJump;
            int end = Math.min(i + maxJump, len - 1);

            statLen = Math.min(statLen, end);

            for (int j = statLen; j <= end; j++) {
                if (dp[j] == -1 || dp[j] == 1) {
                    continue;
                } else {
                    dp[j] = 1;
                }
            }

            statLen = end;
        }

        return dp[len - 1] == 1;
    }
}
