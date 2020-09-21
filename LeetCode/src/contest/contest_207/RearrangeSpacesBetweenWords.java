package contest.contest_207;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 20/9/2020 10:32 AM.
 */
public class RearrangeSpacesBetweenWords {

    public String reorderSpaces(String text) {

        int len = text.length();
        int spaceNum = 0;
        List<String> list = new ArrayList<>();
        String word = "";
        boolean isWord = false;

        char[] chars = text.toCharArray();
        for (int i = 0; i < len; i++) {
            if (' ' == chars[i]) {
                spaceNum++;
                word = "";
                isWord = false;
            } else {
                word += chars[i];
                isWord = true;
            }
            if (isWord && (i == len - 1 || chars[i + 1] == ' ')) {
                list.add(word);
            }
        }

//        System.out.println(list);

        String ans = "";
        int wordCount = list.size();
        if (wordCount == 0) {
            return text;
        } else if (wordCount == 1) {
            ans += list.get(0);
            for (int i = 0; i < spaceNum; i++) {
                ans += " ";
            }
            return ans;
        }

        int everyCount = spaceNum / (wordCount - 1);
        int duoyu = spaceNum % (wordCount - 1);

        String spaceText = "";
        String duoyuText = "";
        for (int i = 0; i < everyCount; i++) {
            spaceText += " ";
        }
        for (int i = 0; i < duoyu; i++) {
            duoyuText += " ";
        }

        for (int i = 0; i < list.size(); i++) {
            ans += list.get(i);
            if (i != wordCount - 1) {
                ans += spaceText;
            }
        }
        ans += duoyuText;

        return ans;
    }

    @Test
    public void test() {
        String result = reorderSpaces("  this   is  a sentence ");

        Assert.assertEquals("this   is   a   sentence", result);
    }

    @Test
    public void test1() {
        String result = reorderSpaces(" practice   makes   perfect");

        Assert.assertEquals("practice   makes   perfect ", result);
    }
}
