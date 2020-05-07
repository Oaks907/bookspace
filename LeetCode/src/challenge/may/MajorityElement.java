package challenge.may;

/**
 * Create by haifei on 7/5/2020 1:12 PM.
 */
public class MajorityElement {

    public int majorityElement(int[] nums) {

        int val = -1;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (i == 0) {
                val = num;
                count++;
                continue;
            }

            if (num == val) {
                count++;
            } else {
                count--;
                if (count <= 0) {
                    val = num;
                    count = 1;
                }
            }
        }
        return val;
    }
}
