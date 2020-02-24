package S201_300;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by haifei on 24/2/2020 7:01 PM.
 */
public class S229_MajorityElementII {

    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || 0 == nums.length) {
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();
        int nums1 = nums[0], nums2 = nums[0], count1 = 0, count2 = 0, len = nums.length;

        for (int item : nums) {
            if (item == nums1) {
                count1++;
            } else if (item == nums2) {
                count2++;
            } else if (count1 == 0) {
                nums1 = item;
                count1 = 1;
            } else if (count2 == 0) {
                nums2 = item;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;

        for (int item : nums) {
            if (item == nums1) {
                count1++;
            } else if (item == nums2) {
                count2++;
            }
        }

        if (count1 > len / 3) {
            result.add(nums1);
        }
        if (count2 > len / 3) {
            result.add(nums2);
        }

        return result;
    }

}
