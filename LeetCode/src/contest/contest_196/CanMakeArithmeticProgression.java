package contest.contest_196;

import java.util.Arrays;

/**
 * Create by haifei on 5/7/2020 10:30 AM.
 */
public class CanMakeArithmeticProgression {

    public boolean canMakeArithmeticProgression(int[] arr) {
        if (arr == null) {
            return false;
        }
        if (arr.length < 2) {
            return true;
        }

        Arrays.sort(arr);
        for (int i = 1; i < arr.length - 1; i++) {
            int cur = arr[i];
            if (arr[i + 1] - cur != cur - arr[i - 1]) {
                return false;
            }
        }
        return true;
    }
}
