package S201_300;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * Create by haifei on 3/4/2020 1:08 PM.
 */
public class S202_HappyNumber {

    public boolean isHappy(int n) {

        Set<Integer> set = new HashSet<>();

        while (n != 1) {

            int sum = 0;

            while (n != 0) {
                sum += (n % 10) * (n % 10);
                n = n / 10;
            }

            n = sum;

            if (set.contains(n)) {
                break;
            } else {
                set.add(n);
            }
        }

        return n == 1;
    }

    @Test
    public void test() {
        System.out.println(isHappy(19));
    }
}
