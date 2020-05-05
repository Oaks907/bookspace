package contest.contest_187;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 3/5/2020 10:32 AM.
 */
public class DestinationCity {

    public String destCity(List<List<String>> paths) {

        Set<String> fromSet = new HashSet<>();
        Set<String> destSet = new HashSet<>();

        for (List<String> path : paths) {
            String from = path.get(0);
            String dest = path.get(1);

            fromSet.add(from);
            destSet.add(dest);
        }

        for (String s : destSet) {
            if (!fromSet.contains(s)) {
                return s;
            }
        }

        return null;
    }

    @Test
    public void test() {

        List<List<String>> list = new ArrayList<>();
        list.add(Arrays.asList("B", "C"));
        list.add(Arrays.asList("D", "B"));
        list.add(Arrays.asList("C", "A"));
        String result = destCity(list);

        Assert.assertEquals("A", result);
    }

}
