package contest.contest_206;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 13/9/2020 5:08 PM.
 */
public class CheckIfStringIsTransformableWithSubstringSortOperations {

    public boolean isTransformable(String s, String t) {

        Queue<Integer>[] queues = new LinkedList[10];

        // 初始化队列
        for (int i = 0; i < queues.length; i++) {
            queues[i] = new LinkedList<>();
        }

        // 将 s 所有的字符放到char型数组位置
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            queues[charArray[i] - '0'].offer(i);
        }

        char[] toCharArray = t.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            int digit = toCharArray[i] - '0';
            // s包含t中的字符，直接返回false
            if (queues[digit].isEmpty()) {
                return false;
            }

            // 出现在digit位置之前的字符位置必须在digit之前，这样保证升序相等
            for (int j = 0; j < digit; j++) {
                if (!queues[j].isEmpty() && queues[j].peek() < queues[digit].peek()) {
                    return false;
                }
            }
            queues[digit].poll();
        }

        return true;
    }

    @Test
    public void test1() {

        boolean result = isTransformable("84532", "34852");

        Assert.assertTrue(result);
    }

    @Test
    public void test2() {

        boolean result = isTransformable("34521", "23415");

        Assert.assertTrue(result);
    }

    @Test
    public void test3() {

        boolean result = isTransformable("12345", "12435");

        Assert.assertFalse(result);
    }

    @Test
    public void test4() {

        boolean result = isTransformable("1", "2");

        Assert.assertFalse(result);
    }
}
