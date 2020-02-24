package S1301_S1400;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import utils.PrintUtils;

/**
 * https://leetcode.com/problems/decompress-run-length-encoded-list/
 * Create by haifei on 24/2/2020 10:54 AM.
 */
public class S1313_DecompressRunLengthEncodedList {

    public int[] decompressRLElist(int[] nums) {

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i = i + 2) {
            int count = nums[i];
            int val = nums[i + 1];
            while (count-- > 0) {
                result.add(val);
            }
        }
        int[] arr = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            arr[i] = result.get(i);
        }
        return arr;
    }

    @Test
    public void test() {

        int[] arr = {5, 27, 44, 42, 53, 6, 55, 64};

        PrintUtils.printArray(decompressRLElist(arr));
    }
}
