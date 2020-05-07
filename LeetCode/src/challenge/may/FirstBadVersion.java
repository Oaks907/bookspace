package challenge.may;

/**
 * Create by haifei on 6/5/2020 1:27 PM.
 */
public class FirstBadVersion {

    public int firstBadVersion(int n) {
        int left = 0;
        int right = n;

        while (left < right) {

            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    boolean isBadVersion(int version) {

        return true;
    }
}
