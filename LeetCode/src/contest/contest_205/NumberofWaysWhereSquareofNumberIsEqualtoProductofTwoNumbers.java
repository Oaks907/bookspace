package contest.contest_205;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 6/9/2020 10:44 AM.
 */
public class NumberofWaysWhereSquareofNumberIsEqualtoProductofTwoNumbers {

    public int numTriplets(int[] nums1, int[] nums2) {

        Map<Double, Integer> powerMap = new HashMap<>();
        Map<Double, Integer> productMap = new HashMap<>();

        for (int i = 0; i < nums1.length; i++) {
            double power = (long) nums1[i] * (long) nums1[i];
            Integer orDefault1 = powerMap.getOrDefault(power, 0);
            powerMap.put(power, orDefault1 + 1);

            for (int j = i + 1; j < nums1.length; j++) {
                double key = (long) nums1[i] * (long) nums1[j];
                Integer orDefault = productMap.getOrDefault(key, 0);
                productMap.put(key, orDefault + 1);
            }
        }

        int ans = 0;

        for (int i = 0; i < nums2.length; i++) {
            double powerVal = (long) nums2[i] * (long) nums2[i];

            if (productMap.containsKey(powerVal)) {
                ans += productMap.get(powerVal);
            }

            for (int j = i + 1; j < nums2.length; j++) {
                double val = (long) nums2[i] * (long) nums2[j];
                if (powerMap.containsKey(val)) {
                    ans += powerMap.get(val);
                }
            }
        }

        return ans;
    }

    @Test
    public void test() {
        int[] arr = {1, 1};
        int[] arr2 = {1, 1, 1};

        int result = numTriplets(arr, arr2);

        Assert.assertEquals(9, result);
    }

    @Test
    public void test1() {
        int[] arr = {43024, 99908};
        int[] arr2 = {1864};

        int result = numTriplets(arr, arr2);

        Assert.assertEquals(0, result);
    }
}
