package S1301_S1400;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Create by haifei on 25/2/2020 8:17 PM.
 */
public class S1352_ProductoftheLastKNumbers {

    private List<Integer> list = new ArrayList<>();

    public S1352_ProductoftheLastKNumbers() {

    }

    public void add(int num) {
        if (num == 0) {
            list.clear();
        } else {
            list.add(list.size() == 0 ? num : list.get(list.size() - 1) * num);
        }
    }

    public int getProduct(int k) {

        if (k > list.size()) {
            return 0;
        } else if (k == list.size()) {
            return list.get(list.size() - 1);
        } else {
            return list.get(list.size() - 1) / list.get(list.size() - 1 - k);
        }
    }

    @Test
    public void test() {
        add(3);
        add(0);
        add(2);
        add(5);
        add(4);
        System.out.println(getProduct(2));
        System.out.println(getProduct(3));
        System.out.println(getProduct(4));
        add(8);
        System.out.println(getProduct(2));
    }
}
