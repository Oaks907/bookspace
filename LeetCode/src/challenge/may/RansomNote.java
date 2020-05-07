package challenge.may;

/**
 * Create by haifei on 7/5/2020 5:25 PM.
 */
public class RansomNote {

    public boolean canConstruct(String ransomNote, String magazine) {
        if (null == ransomNote || ransomNote.isEmpty()) {
            return true;
        }
        if (null == magazine || magazine.isEmpty()) {
            return false;
        }

        String[] split = ransomNote.split("");
        for (int i = 0; i < split.length; i++) {
            String str = split[i];
            if (!magazine.contains(str)) {
                return false;
            }
            magazine = magazine.replaceFirst(str, "");
        }

        return true;
    }
}
