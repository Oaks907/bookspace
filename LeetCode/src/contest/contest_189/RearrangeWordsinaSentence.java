package contest.contest_189;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

/**
 * Create by haifei on 17/5/2020 10:40 AM.
 */
public class RearrangeWordsinaSentence {

    public String arrangeWords(String text) {

        String[] split = text.split(" ");
        Map<Integer, List<String>> map = new TreeMap<>(Integer::compareTo);

        for (int i = 0; i < split.length; i++) {
            String str = split[i];
            if (i == 0) {
                str = str.toLowerCase();
            }
            map.putIfAbsent(str.length(), new ArrayList<>());
            map.get(str.length()).add(str);
        }

        boolean isFirst = true;
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
            List<String> value = entry.getValue();
            for (String s : value) {
                if (isFirst) {
                    char[] strChar = s.toCharArray();
                    strChar[0] -= 32;
                    s = String.valueOf(strChar);
                    isFirst = false;
                }
                sb.append(s).append(" ");
            }
        }

        return sb.toString().trim();
    }

    @Test
    public void test() {
        String result = arrangeWords("Leetcode is cool");
        System.out.println(result);
    }
}
