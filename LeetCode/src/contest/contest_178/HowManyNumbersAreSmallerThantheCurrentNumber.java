package contest.contest_178;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 1/3/2020 10:34 AM.
 */
public class HowManyNumbersAreSmallerThantheCurrentNumber {

    public int[] smallerNumbersThanCurrent(int[] nums) {

        int[] clone = nums.clone();
        Arrays.sort(clone);

        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || (i != 0 && nums[i - 1] != nums[i])) {
                int count = 0;
                for (int j = 0; j < clone.length; j++) {
                    if (clone[j] < nums[i]) {
                        count++;
                    } else {
                        break;
                    }
                }
                res.add(count);
            } else {
                res.add(res.get(res.size() - 1));
                continue;
            }
        }

        int[] arr = new int[res.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = res.get(i);
        }

        return arr;
    }



    @Test
    public void test() {
        int[] arr = {8, 1, 2, 2, 3};

        PrintUtils.printArray(smallerNumbersThanCurrent(arr));
    }

}
