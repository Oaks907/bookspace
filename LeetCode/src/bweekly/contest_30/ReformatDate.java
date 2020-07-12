package bweekly.contest_30;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Create by haifei on 11/7/2020 10:31 PM.
 */
public class ReformatDate {

    String[] month = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    public String reformatDate(String date) {

        Map<String, String> monthMap = new HashMap<>();

        for (int i = 0; i < month.length; i++) {
            String value = String.valueOf(i + 1);
            if (i + 1 < 10) {
                value = "0" + value;
            }
            monthMap.put(month[i], value);
        }

        String[] s = date.split(" ");

        return s[2] + "-" + monthMap.get(s[1]) + "-" + getDay(s[0]);
    }

    private String getDay(String day) {
        if (day.length() == 4) {
            day = day.substring(0, 2);
        } else {
            day = day.substring(0, 1);
        }

        return Integer.parseInt(day) < 10 ? "0" + day : day;
    }

    @Test
    public void test() {
        System.out.println(reformatDate("20th Oct 2052"));
        System.out.println(reformatDate("6th Jun 1933"));
        System.out.println(reformatDate("26th May 1960"));
    }
}
