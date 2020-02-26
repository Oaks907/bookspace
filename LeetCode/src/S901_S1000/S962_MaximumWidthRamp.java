package S901_S1000;

import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 26/2/2020 11:03 PM.
 */
public class S962_MaximumWidthRamp {

    /**
     * 不好的解法
     *
     * @param A
     * @return
     */
    public int maxWidthRamp_(int[] A) {

        int result = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = A.length - 1; j >= i + result; j--) {
                if (A[j] >= A[i]) {
                    result = Math.max(result, j - i);
                }
            }
        }

        return result;
    }

    public int maxWidthRamp(int[] A) {

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < A.length; i++) {
            if (stack.isEmpty() || A[stack.peek()] > A[i]) {
                stack.push(i);
            }
        }

        int res = 0;
        for (int i = A.length - 1; i >= 0; i--) {
            while (!stack.empty() && A[stack.peek()] <= A[i]) {
                res = Math.max(res, i - stack.pop());
            }
        }

        return res;
    }

    @Test
    public void test() {

        int[] arr = {6, 0, 8, 2, 1, 5};

        int result = maxWidthRamp(arr);

        Assert.assertEquals(4, result);
    }

    @Test
    public void test1() {

        int[] arr = {9, 8, 1, 0, 1, 9, 4, 0, 4, 1};

        int result = maxWidthRamp(arr);

        Assert.assertEquals(7, result);
    }
}
