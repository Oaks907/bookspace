package challenge.april;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 21/4/2020 7:41 PM.
 */
public class ValidParenthesisString {

    public boolean checkValidString(String s) {

        if (null == s) {
            return false;
        }
        if (s.isEmpty()) {
            return true;
        }

        while (s.contains("()")) {
            s = s.replace("()", "");
        }

//        System.out.println(s);

        List<Character> list = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (c == '(') {
                list.add('(');
            } else if (c == ')') {
                int findIndex = -1;
                for (int j = list.size() - 1; j >= 0; j--) {
                    if (list.get(j) == '(') {
                        findIndex = j;
                        break;
                    }
                }
                if (findIndex != -1) {
                    list.remove(findIndex);
                } else if (list.size() != 0) {
                    list.remove(list.size() - 1);
                } else {
                    return false;
                }
            } else if (c == '*') {
                list.add('*');
            }
        }

//        System.out.println(list);

        int left = 0;
        int xing = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            Character c = list.get(i);
            if (c == '*') {
                xing++;
            } else {
                left++;
            }
            if (left > xing) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void test4() {

        boolean result = checkValidString("(())((())()()(*)(*()(())())())()()((()())((()))(*");

        Assert.assertFalse(result);
    }

    @Test
    public void test3() {

        boolean result = checkValidString(")(");

        Assert.assertFalse(result);
    }

    @Test
    public void test() {

        boolean result = checkValidString("()");

        Assert.assertTrue(result);
    }

    @Test
    public void test1() {

        boolean result = checkValidString("(*)");

        Assert.assertTrue(result);
    }

    @Test
    public void test2() {

        boolean result = checkValidString("(*))");

        Assert.assertTrue(result);
    }
}
