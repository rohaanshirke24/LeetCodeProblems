class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;
        int NEG = Integer.MIN_VALUE / 4;

        int[][][] dp = new int[m][n][3];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], NEG);
            }
        }

        dp[0][0][0] = coins[0][0];
        if (coins[0][0] < 0) {
            dp[0][0][1] = 0;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;

                for (int used = 0; used <= 2; used++) {
                    int bestPrev = NEG;

                    if (i > 0) bestPrev = Math.max(bestPrev, dp[i - 1][j][used]);
                    if (j > 0) bestPrev = Math.max(bestPrev, dp[i][j - 1][used]);

                    if (bestPrev != NEG) {
                        dp[i][j][used] = Math.max(dp[i][j][used], bestPrev + coins[i][j]);
                    }

                    if (coins[i][j] < 0 && used > 0) {
                        int bestNeutral = NEG;
                        if (i > 0) bestNeutral = Math.max(bestNeutral, dp[i - 1][j][used - 1]);
                        if (j > 0) bestNeutral = Math.max(bestNeutral, dp[i][j - 1][used - 1]);

                        if (bestNeutral != NEG) {
                            dp[i][j][used] = Math.max(dp[i][j][used], bestNeutral);
                        }
                    }
                }
            }
        }

        return Math.max(dp[m - 1][n - 1][0],
               Math.max(dp[m - 1][n - 1][1], dp[m - 1][n - 1][2]));
    }
}