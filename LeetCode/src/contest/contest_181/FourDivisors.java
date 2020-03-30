package contest.contest_181;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 22/3/2020 10:50 AM.
 */
public class FourDivisors {

    public int sumFourDivisors_TLE(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            int val = helper(nums[i]);
            if (val != -1) {
                result += val;
            }
        }
        return result;
    }

    public int helper(int n) {
        int count = 0;
        int sum = 0;

        if (n == 0) {
            return 0;
        } else {
            for (int i = 1; i <= n; i++) {
                if (n % i == 0) {
                    count += 1;
                    sum += i;
                    if (count > 4) {
                        return -1;
                    }
                }
            }
        }

        if (count == 4) {
            return sum;
        } else {
            return -1;
        }
    }

    public int sumFourDivisors(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            int val = factors(nums[i]);
            if (val != -1) {
                result += val;
            }
        }
        return result;
    }

    /**
     * 求正整数 N的因子数
     *
     * @param N
     * @return
     */
    public int factors(int N) {
        if (1 == N) {
            return -1;
        }
        int count = 2;// 1 与 N 必是
        final int sqrt_N = (int) Math.sqrt(N);
        int r;

        int sum = 0;

        for (int i = 2; i <= sqrt_N; i++) {
            if (0 == N % i) {
                if (i == sqrt_N) {
                    r = N / i;
                    if (r == i) {//比如 4 = 2 * 2；那么2 只能算一个
                        count++;
                        sum += r;
                    } else {
                        count += 2;

                        sum += r;
                        sum += i;
                    }
                } else {
                    r = N / i;
                    sum += r;
                    sum += i;

                    count += 2;
                }

                if (count > 4) {
                    return -1;
                }
            }
        }
        if (count != 4) {
            return -1;
        }
        sum += 1;
        sum += N;

        System.out.println(sum);

        return sum;
    }

    @Test
    public void test() {
        int[] arr = {21, 4, 7};

        int result = sumFourDivisors(arr);

        Assert.assertEquals(32, result);

    }

    @Test
    public void test1() {
        int[] arr = {1, 2, 3, 4, 5};

        int result = sumFourDivisors(arr);

        Assert.assertEquals(0, result);

    }
}
