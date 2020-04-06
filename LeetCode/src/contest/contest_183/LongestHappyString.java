package contest.contest_183;

import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 5/4/2020 11:43 AM.
 */
public class LongestHappyString {

    public String longestDiverseString(int a, int b, int c) {

        Queue<int[]> queue = new PriorityQueue<>((x, y) -> y[1] - x[1]);
        queue.add(new int[] {0, a});
        queue.add(new int[] {1, b});
        queue.add(new int[] {2, c});

        StringBuilder sb = new StringBuilder();

        while (true) {

            int[] most = queue.poll();
            int N = sb.length();
            char ch = (char) (most[0] + 'a');

            // 次数变为了0，直接返回
            if (most[1] == 0) {
                break;
            }

            // 在满足条件的情况下，这里填充最大次数的数字
            if (N < 2 || (sb.charAt(N - 2) != ch) || (sb.charAt(N - 1)) != ch) {

                sb.append(ch);
                most[1]--;
            } else {
                // 最大出现次数的数字已使用，使用第二大的数字


                if (queue.peek()[1] == 0)  {
                    break;
                }

                int[] second = queue.poll();
                ch = (char) (second[0] + 'a');
                sb.append(ch);
                second[1]--;
                queue.add(second);
            }
            queue.add(most);
        }

        return sb.toString();
    }

    @Test
    public void test() {

        String result = longestDiverseString(1, 1, 7);

        Assert.assertEquals("ccaccbcc", result);
    }

    @Test
    public void test2() {

        String result = longestDiverseString(2, 2, 1);

        Assert.assertEquals("aabbc", result);
    }

    @Test
    public void test3() {

        String result = longestDiverseString(7, 1, 0);

        Assert.assertEquals("aabaa", result);
    }
}
