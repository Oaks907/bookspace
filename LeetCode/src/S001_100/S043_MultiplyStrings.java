package S001_100;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 13/8/2020 1:06 PM.
 */
public class S043_MultiplyStrings {

    public String multiply(String num1, String num2) {

        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        String res = "0";

        for (int i = num2.length() - 1; i >= 0; i--) {

            int n2 = num2.charAt(i) - '0';

            int carry = 0;

            // 保存 num2 第i位数字与 num1 相乘的结果
            StringBuilder temp = new StringBuilder();

            // 补 0
            for (int j = 0; j < num2.length() - 1 - i; j++) {
                temp.append(0);
            }

            for (int j = num1.length() - 1; j >= 0 || carry != 0; j--) {
                int n1 = j < 0 ? 0 : num1.charAt(j) - '0';
                int product = (n1 * n2 + carry) % 10;
                temp.append(product);
                carry = (n1 * n2 + carry) / 10;
            }

            res = addStrings(res, temp.reverse().toString());
        }
        return res;
    }

    /**
     * 对两个字符串数字进行相加，返回字符串形式的和
     */
    public String addStrings(String num1, String num2) {
        StringBuilder builder = new StringBuilder();
        int carry = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0 || carry != 0; i--, j--) {
            int x = i < 0 ? 0 : num1.charAt(i) - '0';
            int y = j < 0 ? 0 : num2.charAt(j) - '0';
            int sum = (x + y + carry) % 10;
            builder.append(sum);
            carry = (x + y + carry) / 10;
        }
        return builder.reverse().toString();
    }

    @Test
    public void test() {

        String result = multiply("123", "456");

        Assert.assertEquals(String.valueOf(123 * 456), result);
    }
}
