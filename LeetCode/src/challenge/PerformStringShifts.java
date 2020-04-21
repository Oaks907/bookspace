package challenge;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 20/4/2020 11:38 PM.
 */
public class PerformStringShifts {

    public String stringShift(String s, int[][] shift) {

        int direction = -1;
        int length = 0;
        for (int i = 0; i < shift.length; i++) {
            if (direction == -1) {
                direction = shift[i][0];
                length = shift[i][1];
                continue;
            }
            if (direction != shift[i][0]) {
                direction = shift[i][1] > length ? shift[i][0] : direction;
                length = Math.abs(length - shift[i][1]);
            } else {
                length += shift[i][1];
            }
        }

        System.out.println(direction + " " + length);

        return shift(s, direction, length % s.length());
    }

    private String shift(String s, int shiftDirection, int length) {
        if (shiftDirection == 0) {
            String left = s.substring(0, length);
            String right = s.substring(length, s.length());
            return right + left;
        } else {
            String left = s.substring(0, s.length() - length);
            String right = s.substring(s.length() - length);
            return right + left;
        }
    }

    @Test
    public void test3() {

        int[][] arr = {{1, 8}, {1, 4}, {1, 3}, {1, 6}, {0, 6}, {1, 4}, {0, 2}, {0, 1}};

        String result = stringShift("yisxjwry", arr);

        System.out.println(result);

        Assert.assertEquals("kmecs", result);
    }

    @Test
    public void test2() {

        int[][] arr = {{1, 4}, {0, 5}, {0, 4}, {1, 1}, {1, 5}};

        String result = stringShift("mecsk", arr);

        System.out.println(result);

        Assert.assertEquals("kmecs", result);
    }

    @Test
    public void test() {

        int[][] arr = {{0, 1}, {1, 2}};

        String result = stringShift("abc", arr);

        System.out.println(result);
    }

    @Test
    public void test1() {

        int[][] arr = {{1, 1}, {1, 1}, {0, 2}, {1, 3}};

        String result = stringShift("abcdefg", arr);

        System.out.println(result);
    }
}
