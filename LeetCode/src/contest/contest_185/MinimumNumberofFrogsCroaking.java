package contest.contest_185;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 19/4/2020 11:20 AM.
 */
public class MinimumNumberofFrogsCroaking {

    // TLE
    public int minNumberOfFrogs_TLE(String croakOfFrogs) {

        String croak = "croak";
        List<Stack<Character>> list = new ArrayList<>();
        int result = 0;

        for (int i = 0; i < croakOfFrogs.length(); i++) {

            char c = croakOfFrogs.charAt(i);
            if (!croak.contains(String.valueOf(c))) {
                return -1;
            }

            if (croak.indexOf(c) == 0) {
                Stack<Character> stack = new Stack<>();
                stack.push(c);
                list.add(stack);
                result = Math.max(result, list.size());
                if (result > croakOfFrogs.length() / croak.length()) {
                    return -1;
                }
                continue;
            }

            boolean hadDeal = false;

            for (int j = list.size() - 1; j >= 0; j--) {

                Stack<Character> stack = list.get(j);
                int index = croak.indexOf(c);

                if (!stack.isEmpty() && stack.peek() == croak.charAt(index - 1)) {
                    hadDeal = true;
                    if (croak.length() - 1 == index) {
                        list.remove(j);
                    } else {
                        stack.push(c);
                    }
                    break;
                }
            }
            if (!hadDeal) {
                return -1;
            }
        }

        if (!list.isEmpty()) {
            return -1;
        }

        return result;
    }

    public int minNumberOfFrogs(String croakOfFrogs) {

        int[] cnt = new int[5];
        int frogs = 0;
        int maxFrogs = 0;
        for (int i = 0; i < croakOfFrogs.length(); i++) {

            char c = croakOfFrogs.charAt(i);
            int index = "croak".indexOf(c);
            cnt[index]++;
            if (index == 0) {
                maxFrogs = Math.max(maxFrogs, ++frogs);
            } else if (--cnt[index - 1] < 0) {
                return -1;
            } else if (index == 4) {
                --frogs;
            }
        }

        return frogs == 0 ? maxFrogs : -1;
    }


    @Test
    public void test4() {

        int result = minNumberOfFrogs("crocakcroraoakk");

        Assert.assertEquals(2, result);
    }

    @Test
    public void test() {

        int result = minNumberOfFrogs("croakcroak");

        Assert.assertEquals(1, result);
    }

    @Test
    public void test1() {

        int result = minNumberOfFrogs("crcoakroak");

        Assert.assertEquals(2, result);
    }

    @Test
    public void test2() {

        int result = minNumberOfFrogs("croakcrook");

        Assert.assertEquals(-1, result);
    }

    @Test
    public void test3() {

        int result = minNumberOfFrogs("croakcroa");

        Assert.assertEquals(-1, result);
    }
}
