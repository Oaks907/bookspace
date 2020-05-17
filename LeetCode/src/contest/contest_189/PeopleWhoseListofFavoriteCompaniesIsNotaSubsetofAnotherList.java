package contest.contest_189;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

/**
 * Create by haifei on 17/5/2020 10:59 AM.
 */
public class PeopleWhoseListofFavoriteCompaniesIsNotaSubsetofAnotherList {

    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {

        Map<String, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < favoriteCompanies.size(); i++) {
            List<String> list = favoriteCompanies.get(i);
            for (String s : list) {
                map.putIfAbsent(s, new ArrayList<>());
                map.get(s).add(i);
            }
        }


        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < favoriteCompanies.size(); i++) {
            List<String> list = favoriteCompanies.get(i);
            ArrayList<Integer> arrayList = new ArrayList<>();

            for (int j = 0; j < list.size(); j++) {
                List<Integer> integers = map.get(list.get(j));
                if (j == 0) {
                    arrayList.addAll(integers);
                    continue;
                } else {
                    arrayList.retainAll(integers);
                }
            }


            if (arrayList.size() == 1) {
                result.add(i);
            }
        }

        return result;
    }

    @Test
    public void test() {
        List<List<String>> favoriteCompanies = new ArrayList<>();
        favoriteCompanies.add(Arrays.asList("leetcode","google","facebook"));
        favoriteCompanies.add(Arrays.asList("google","microsoft"));
        favoriteCompanies.add(Arrays.asList("google","facebook"));
        favoriteCompanies.add(Arrays.asList("google"));
        favoriteCompanies.add(Arrays.asList("amazon"));

        System.out.println(peopleIndexes(favoriteCompanies));
    }

}
