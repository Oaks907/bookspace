package bweekly.contest_29;

import java.util.Arrays;

import org.junit.Test;

/**
 * Create by haifei on 27/6/2020 10:31 PM.
 */
public class Average {

    public double average(int[] salary) {

        double sum = 0;

        Arrays.sort(salary);
        for (int i = 1; i < salary.length - 1; i++) {
            sum += salary[i];
        }

        return (double) (sum / (salary.length - 2));
    }

    @Test
    public void test() {
        int[] arr = {48000,59000,99000,13000,78000,45000,31000,17000,39000,37000,93000,77000,33000,28000,4000,54000,
                67000,6000,1000,11000};

        System.out.println(average(arr));
    }
}
