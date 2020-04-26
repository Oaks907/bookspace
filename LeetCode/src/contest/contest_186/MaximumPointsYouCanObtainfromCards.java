package contest.contest_186;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 26/4/2020 9:35 PM.
 */
public class MaximumPointsYouCanObtainfromCards {

    // TLE
    public int maxScore_TLE(int[] cardPoints, int k) {
        if (k >= cardPoints.length) {
            int sum = 0;
            for (int i = 0; i < cardPoints.length; i++) {
                sum += cardPoints[i];
            }
            return sum;
        }

        return helper(cardPoints, k, 0, cardPoints.length - 1, 0);
    }

    public int helper(int[] cardPoints, int k, int left, int right, int score) {
        if (k == 0 || left > right) {
            return score;
        }

        // left
        int leftScore = helper(cardPoints, k - 1, left + 1, right, score + cardPoints[left]);
        // right
        int rightScore = helper(cardPoints, k - 1, left, right - 1, score + cardPoints[right]);

        return Math.max(leftScore, rightScore);
    }

    public int maxScore(int[] cardPoints, int k) {

        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += cardPoints[i];
        }

        int max = sum;
        for (int i = 1; i <= k; i++) {
            sum = sum + cardPoints[cardPoints.length - i] - cardPoints[k - i];
            max = Math.max(sum, max);
        }

        return max;
    }

    @Test
    public void test() {

        int[] arr = {1, 2, 3, 4, 5, 6, 1};

        int res = maxScore(arr, 3);

        Assert.assertEquals(12, res);
    }

    @Test
    public void test1() {

        int[] arr = {2, 2, 2};

        int res = maxScore(arr, 2);

        Assert.assertEquals(4, res);
    }

    @Test
    public void test2() {

        int[] arr = {9, 7, 7, 9, 7, 7, 9};

        int res = maxScore(arr, 7);

        Assert.assertEquals(55, res);
    }

    @Test
    public void test3() {

        int[] arr = {1, 1000, 1};

        int res = maxScore(arr, 1);

        Assert.assertEquals(1, res);
    }

    @Test
    public void test4() {

        int[] arr = {1, 79, 80, 1, 1, 1, 200, 1};

        int res = maxScore(arr, 3);

        Assert.assertEquals(202, res);
    }
}
