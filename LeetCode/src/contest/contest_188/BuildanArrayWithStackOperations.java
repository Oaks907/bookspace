package contest.contest_188;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Create by haifei on 10/5/2020 11:05 AM.
 */
public class BuildanArrayWithStackOperations {

    String POP = "Pop";
    String PUSH = "Push";

    public List<String> buildArray(int[] target, int n) {

        List<String> result = new ArrayList<>();
        int start = 0;
        for (int i = 1; i <= n; i++) {
            if (start < target.length) {
                if (target[start] == i) {
                    start++;
                    result.add(PUSH);
                    if (start == target.length) {
                        break;
                    }
                } else {
                    result.add(PUSH);
                    result.add(POP);
                }
            } else {
                result.add(PUSH);
                result.add(POP);
            }
        }

        return result;
    }

    @Test
    public void test() {

        int[] arr = {1, 2};

        List<String> list = buildArray(arr, 4);

        System.out.println(list);
    }
}
