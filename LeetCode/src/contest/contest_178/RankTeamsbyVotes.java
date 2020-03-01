package contest.contest_178;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 1/3/2020 11:27 AM.
 */
public class RankTeamsbyVotes {

    public String rankTeams(String[] votes) {

        HashMap<Character, int[]> map = new HashMap<>();
        int len = votes[0].length();

        for (int i = 0; i < votes.length; i++) {
            for (int j = 0; j < len; j++) {
                char c = votes[i].charAt(j);
                map.computeIfAbsent(c, k -> new int[len]);
                map.get(c)[j]++;
            }
        }

        ArrayList<Character> list = new ArrayList<>(map.keySet());
        Collections.sort(list, (a, b) -> {
            for (int i = 0; i < len; i++) {
                if (map.get(a)[i] != map.get(b)[i]) {
                    return map.get(b)[i] - map.get(a)[i];
                }
            }
            return a - b;
        });


        StringBuilder sb = new StringBuilder();
        for(char c : list){
            sb.append(c);
        }
        return sb.toString();
    }

    @Test
    public void test() {

        String[] arr = {"WXYZ", "XYZW"};

        String result = rankTeams(arr);

        Assert.assertEquals("XWYZ", result);
    }

    @Test
    public void test1() {

        String[] arr = {"ABC", "ACB", "ABC", "ACB", "ACB"};

        String result = rankTeams(arr);

        Assert.assertEquals("ACB", result);
    }

    @Test
    public void test3() {

        String[] arr = {"ZMNAGUEDSJYLBOPHRQICWFXTVK"};

        String result = rankTeams(arr);

        Assert.assertEquals("ZMNAGUEDSJYLBOPHRQICWFXTVK", result);
    }

    @Test
    public void test2() {

        String[] arr = {"FVSHJIEMNGYPTQOURLWCZKAX", "AITFQORCEHPVJMXGKSLNZWUY"};

        String result = rankTeams(arr);
        System.out.println(result);
        System.out.println(result);

        Assert.assertEquals("FAIVTSHJQOERMCNGPYXUKLWZ", result);
    }
}

