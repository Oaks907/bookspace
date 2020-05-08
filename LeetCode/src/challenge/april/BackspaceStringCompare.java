package challenge.april;

/**
 * Create by haifei on 12/4/2020 9:29 PM.
 */
public class BackspaceStringCompare {

    public boolean backspaceCompare(String S, String T) {

        String strS = helper(S);
        String strT = helper(T);

        return strS.equals(strT);
    }

    private String helper(String str) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '#' && sb.length() != 0) {
                sb.deleteCharAt(sb.length() - 1);
            } else if (str.charAt(i) != '#') {
                sb.append(str.charAt(i));
            }
        }

        return sb.toString();
    }
}
