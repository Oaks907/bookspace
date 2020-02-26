package S701_800;

/**
 * https://leetcode.com/problems/global-and-local-inversions/
 * Create by haifei on 26/2/2020 11:03 AM.
 */
public class S775_GlobalandLocalInversions {

    public boolean isIdealPermutation(int[] A) {

        int maxVal = 0;
        for (int i = 0; i < A.length - 2; i++) {
            maxVal = Math.max(maxVal, A[i]);

            if (maxVal > A[i + 2]) {
                return false;
            }
        }
        return true;
    }
}
