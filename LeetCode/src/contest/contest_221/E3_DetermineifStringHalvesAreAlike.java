package contest.contest_221;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Create by haifei on 27/12/2020 10:31 AM.
 */
public class E3_DetermineifStringHalvesAreAlike {

    public boolean halvesAreAlike(String s) {
        char[] charArray = s.toCharArray();
        int left = 0;
        int right = charArray.length - 1;
        int countLeft = 0, countRight = 0;
        List<Character> list = Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');

        while (left < right) {
            if (list.contains(charArray[left])) {
                countLeft++;
            }
            if (list.contains(charArray[right])) {
                countRight++;
            }
            left++;
            right--;
        }
        return countLeft == countRight;
    }

    @Test
    public void test() {
        System.out.println(halvesAreAlike("Uaec"));
    }
}
