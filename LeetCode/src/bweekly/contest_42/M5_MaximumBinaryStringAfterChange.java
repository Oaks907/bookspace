package bweekly.contest_42;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 26/12/2020 11:35 PM.
 */
public class M5_MaximumBinaryStringAfterChange {

    /**
     * 超时
     *
     * @param binary
     * @return
     */
    public String maximumBinaryStrin_TEL(String binary) {
        char[] binaryChar = binary.toCharArray();
        int preZeroNum = 0;

        for (int i = 0; i < binaryChar.length; i++) {
            char cur = binaryChar[i];
            if (cur == '1') {
                continue;
            } else if (cur == '0') {
                if (preZeroNum > 0) {
                    int index = i - 1;
                    binaryChar[i] = '0';
                    while (index >= 0 && binaryChar[index] == '1') {
                        index--;
                    }
                    if (index >= 0) {
                        if (index != i - 1) {
                            if (index == i - 2) {
                                binaryChar[index + 1] = '0';
                                binaryChar[index + 2] = '1';
                            } else {
                                binaryChar[i] = '1';
                                binaryChar[index] = '1';
                                binaryChar[index + 1] = '0';
                            }
                            i = index;
                        } else {
                            binaryChar[i - 1] = '1';
                        }
                    }
                } else {
                    preZeroNum = 1;
                }
            }
            //            System.out.println(String.valueOf(binaryChar));
        }

        return String.valueOf(binaryChar);
    }

    public String maximumBinaryString(String binary) {
        int length = binary.length();
        char[] charArray = binary.toCharArray();

        int countZero = 0;
        int firstZero = -1;
        for (int i = 0; i < length; i++) {
            if (charArray[i] == '0') {
                countZero++;
            }
            if (firstZero == -1 && charArray[i] == '0') {
                firstZero = i;
            }
        }

        if (countZero == 0) {
            return binary;
        }

        int countOne = length - countZero;
        for (int i = 0; i < length; i++) {
            if (i == (countZero + firstZero - 1)) {
                charArray[i] = '0';
            } else {
                charArray[i] = '1';
            }
        }
        System.out.println((countZero + firstZero));

        return String.valueOf(charArray);
    }

    @Test
    public void test() {
        String result = maximumBinaryString("000110");
        Assert.assertEquals("111011", result);
    }

    @Test
    public void test1() {
        String result = maximumBinaryString("01");
        Assert.assertEquals("01", result);
    }

    @Test
    public void test2() {
        String result = maximumBinaryString("01110");
        Assert.assertEquals("10111", result);
    }
}
