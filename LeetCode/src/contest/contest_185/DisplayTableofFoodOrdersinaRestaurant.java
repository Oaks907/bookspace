package contest.contest_185;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Test;

import com.sun.prism.TextureMap;

/**
 * Create by haifei on 19/4/2020 10:51 AM.
 */
public class DisplayTableofFoodOrdersinaRestaurant {

    public List<List<String>> displayTable(List<List<String>> orders) {

        Set<String> food = new TreeSet<>();
        Map<String, Map<String, Integer>> treeMap = new TreeMap<>(Comparator.comparingInt(Integer::parseInt));

        for (List<String> order : orders) {

            String table = order.get(1);
            String foodName = order.get(2);
            food.add(foodName);

            Map<String, Integer> orDefault = treeMap.getOrDefault(table, new HashMap<>());
            orDefault.put(foodName, orDefault.getOrDefault(foodName, 0) + 1);
            treeMap.put(table, orDefault);
        }

        List<List<String>> result = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        temp.add("Table");
        for (String foodName : food) {
            temp.add(foodName);
        }
        result.add(temp);

        for (Map.Entry<String, Map<String, Integer>> item : treeMap.entrySet()) {
            ArrayList<String> list = new ArrayList<>();
            list.add(item.getKey());

            Map<String, Integer> value = item.getValue();
            for (String foodName : food) {
                list.add(String.valueOf(value.getOrDefault(foodName, 0)));
            }
            result.add(list);
        }

        return result;
    }

    @Test
    public void test() {

    }
}
