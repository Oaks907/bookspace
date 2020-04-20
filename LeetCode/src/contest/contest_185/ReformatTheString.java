package contest.contest_185;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Create by haifei on 19/4/2020 10:37 AM.
 */
public class ReformatTheString {

    public String reformat(String s) {

        List<Character> digitList = new ArrayList<>();
        List<Character> englishList = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                digitList.add(c);
            } else {
                englishList.add(c);
            }
        }

        if (Math.abs(digitList.size() - englishList.size()) > 1) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int len = Math.min(digitList.size(), englishList.size());
        boolean digitBetter = (digitList.size() == len);
        for (int i = 0; i < len; i++) {
            if (digitBetter) {
                sb.append(englishList.get(i));
                sb.append(digitList.get(i));
            } else {
                sb.append(digitList.get(i));
                sb.append(englishList.get(i));
            }
        }

        for (int i = len; i < digitList.size(); i++) {
            sb.append(digitList.get(i));
        }

        for (int i = len; i < englishList.size(); i++) {
            sb.append(englishList.get(i));
        }

        return sb.toString();
    }

    @Test
    public void test() {

        String result = reformat("a0b1c2");

        System.out.println(result);
    }
}
