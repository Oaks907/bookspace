package contest.contest_207;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 21/9/2020 12:48 PM.
 */
public class MinimumCosttoConnectTwoGroupsofPoints {

    public int connectTwoGroups(List<List<Integer>> cost) {
        
        return 0;
    }

    @Test
    public void test() {

        List<List<Integer>> cost = new ArrayList<>();
        cost.add(Arrays.asList(1, 3, 5));
        cost.add(Arrays.asList(4, 1, 1));
        cost.add(Arrays.asList(1, 5, 3));

        int result = connectTwoGroups(cost);

        Assert.assertEquals(4, result);
    }
}
