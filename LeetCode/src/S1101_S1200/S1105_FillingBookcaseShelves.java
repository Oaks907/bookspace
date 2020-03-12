package S1101_S1200;

/**
 * Create by haifei on 10/3/2020 1:06 PM.
 */
public class S1105_FillingBookcaseShelves {

    public int minHeightShelves(int[][] books, int shelf_width) {

        int row = books.length;
        int col = books[0].length;

        // 记录当前最佳高度
        int[] dp = new int[row + 1];
        dp[0] = 0;

        for (int i = 1; i <= row; i++) {

            int width = books[i - 1][0];
            int height = books[i - 1][1];

            dp[i] = dp[i - 1] + height;

            for (int j = i - 1; j > 0 && width + books[j - 1][0] <= shelf_width; j--) {
                height = Math.max(height, books[j - 1][1]);
                width += books[j - 1][0];
                dp[i] = Math.min(dp[i], dp[j - 1] + height);
            }
        }

        return dp[books.length];
    }
}
