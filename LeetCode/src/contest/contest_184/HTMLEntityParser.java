package contest.contest_184;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 12/4/2020 11:03 AM.
 */
public class HTMLEntityParser {

    public String entityParser(String text) {

        if (text.contains("&quot;")) {
            text = text.replace("&quot;", "\"");
        }
        if (text.contains("&apos;")) {
            text = text.replace("&apos;", "'");
        }
        if (text.contains("&amp;")) {
            text = text.replace("&amp;", "&");
        }
        if (text.contains("&gt;")) {
            text = text.replace("&gt;", ">");
        }
        if (text.contains("&lt;")) {
            text = text.replace("&lt;", "<");
        }
        if (text.contains("&frasl;")) {
            text = text.replace("&frasl;", "/");
        }

        return text;
    }

    @Test
    public void test1() {
        String text = "&amp; is an HTML entity but &ambassador; is not.";

        String result = entityParser(text);

        Assert.assertEquals("& is an HTML entity but &ambassador; is not.", result);
    }

    @Test
    public void test2() {
        String text = "and I quote: &quot;...&quot;";

        String result = entityParser(text);

        Assert.assertEquals("and I quote: \"...\"", result);
    }

    @Test
    public void test3() {
        String text = "Stay home! Practice on Leetcode :)";

        String result = entityParser(text);

        Assert.assertEquals("Stay home! Practice on Leetcode :)", result);
    }

    @Test
    public void test4() {
        String text = "x &gt; y &amp;&amp; x &lt; y is always false";

        String result = entityParser(text);

        Assert.assertEquals("x > y && x < y is always false", result);
    }

    @Test
    public void test5() {
        String text = "leetcode.com&frasl;problemset&frasl;all";

        String result = entityParser(text);

        Assert.assertEquals("leetcode.com/problemset/all", result);
    }

    @Test
    public void test6() {
        String text = "&amp;";
    }

}
