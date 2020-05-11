package challenge.may;

/**
 * Create by haifei on 11/5/2020 1:01 PM.
 */
public class ValidPerfectSquare {

    public boolean isPerfectSquare(int num) {
        int i = 1;

        while (num > 0) {
            num -= i;
            i += 2;
        }

        return num == 0;
    }
}
