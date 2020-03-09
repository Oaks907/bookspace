package contest.contest_179;

import org.junit.Test;

/**
 * Create by haifei on 8/3/2020 11:05 AM.
 */
public class GenerateaStringWithCharactersThatHaveOddCounts {

    public String generateTheString(int n) {
        String a = "a";
        String b = "b";

        String result = "";

        if (n % 2 == 0) {
            while (n-- > 1) {
                result += a;
            }
            result += b;
        } else {
            while (n-- > 0) {
                result += a;
            }
        }

        return result;
    }

    @Test
    public void test() {
        System.out.println(generateTheString(4));
        System.out.println(generateTheString(2));
        System.out.println(generateTheString(7));
    }
}
