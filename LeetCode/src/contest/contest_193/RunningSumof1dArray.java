package contest.contest_193;

/**
 * Create by haifei on 14/6/2020 10:33 AM.
 */
public class RunningSumof1dArray {

    public int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i-1];
        }
        return nums;
    }
}
