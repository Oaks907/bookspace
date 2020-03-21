package contest.contest_34;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 21/3/2020 7:34 PM.
 */
public class MinimumIndexSumofTwoLists {

    public String[] findRestaurant(String[] list1, String[] list2) {


        HashMap<String, Integer> map = new HashMap<>();
        int minIndex = Integer.MAX_VALUE;
        List<String> result = new ArrayList<String>();

        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
            result.add(list1[i]);
        }

        for (int i = 0; i < list2.length; i++) {
            Integer orDefault = map.getOrDefault(list2[i], 0);
            orDefault += i;

            if (!map.containsKey(list2[i])) {
                map.put(list2[i], orDefault);
                continue;
            }

            map.put(list2[i], orDefault);


            if (orDefault == minIndex) {
                result.add(list2[i]);
            } else if (orDefault < minIndex) {
                result.clear();
                result.add(list2[i]);
                minIndex = orDefault;
            }
        }

        return result.toArray(new String[result.size()]);
    }

    @Test
    public void test() {

        String[] strArrOne = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] strArrTwo = {"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"};

        String[] result = findRestaurant(strArrOne, strArrTwo);

        PrintUtils.printArray(result);
    }

    @Test
    public void test1() {

        String[] strArrOne = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] strArrTwo = {"KFC", "Shogun", "Burger King"};

        String[] result = findRestaurant(strArrOne, strArrTwo);

        PrintUtils.printArray(result);
    }
}
