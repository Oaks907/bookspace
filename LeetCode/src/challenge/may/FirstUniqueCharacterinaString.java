package challenge.may;

/**
 * Create by haifei on 6/5/2020 1:20 PM.
 */
public class FirstUniqueCharacterinaString {

    public int firstUniqChar(String s) {

        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            arr[c - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (arr[c - 'a'] == 1) {
                return i;
            }
        }

        return -1;
    }
}
