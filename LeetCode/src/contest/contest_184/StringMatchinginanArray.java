package contest.contest_184;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 12/4/2020 10:36 AM.
 */
public class StringMatchinginanArray {

    public List<String> stringMatching(String[] words) {

        Arrays.sort(words, (o1, o2) -> o2.length() - o1.length());
        ArrayList<String> result = new ArrayList<>();

        for (int i = 1; i < words.length; i++) {
            String cur = words[i];
            for (int j = 0; j < i; j++) {
                if (words[j].contains(cur)) {
                    result.add(cur);
                    break;
                }
            }
        }

        return result;
    }

    @Test
    public void test() {
        String[] words = {"mass", "as", "hero", "superhero"};

        System.out.println(stringMatching(words));
    }

    @Test
    public void test1() {
        String[] words = {"leetcoder", "leetcode", "od", "hamlet", "am"};

        System.out.println(stringMatching(words));
    }
}
