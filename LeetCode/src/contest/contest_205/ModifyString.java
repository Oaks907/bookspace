package contest.contest_205;

/**
 * Create by haifei on 6/9/2020 10:31 AM.
 */
public class ModifyString {

    public String modifyString(String s) {
        s = "a" + s + "a";

        for (int i = 1; i < s.length() - 1; i++) {
            char cur = s.charAt(i);
            if (cur == '?') {
                char pre = s.charAt(i - 1);
                char next = s.charAt(i + 1);

                for (int j = 0; j < 26; j++) {
                    if ('a' + j != pre && 'a' + j != next) {
                        s = s.replaceFirst("\\?", String.valueOf((char) ('a' + j)));
                        break;
                    }
                }
            }
        }

        return s.substring(1, s.length() - 1);
    }

}
