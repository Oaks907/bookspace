package S001_100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * Create by haifei on 31/3/2020 11:30 PM.
 */
public class S049_GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {

        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {

            char[] chars = new char[26];

            for (int i = 0; i < str.length(); i++) {
                chars[str.charAt(i) - 'a'] += 1;
            }

            String key = String.valueOf(chars);
            List<String> orDefault = map.getOrDefault(key, new ArrayList<>());
            orDefault.add(str);
            map.put(key, orDefault);
        }

        return new ArrayList<>(map.values());
    }

    @Test
    public void test() {

        String[] str = {"eat", "tea", "tan", "ate", "nat", "bat"};

        List<List<String>> result = groupAnagrams(str);

        System.out.println(result);
    }
}
