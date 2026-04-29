class Solution {
    public long maximumScore(int[][] grid) {
        int n = grid.length;
        int m = n + 1; 
        long NEG = Long.MIN_VALUE / 4;
        long[][] pref = new long[n][n + 1];
        for (int c = 0; c < n; c++) {
            for (int r = 0; r < n; r++) {
                pref[c][r + 1] = pref[c][r] + grid[r][c];
            }
        }
        long[][] dp = new long[m][m];
        for (int i = 0; i < m; i++) Arrays.fill(dp[i], NEG);
        for (int h0 = 0; h0 < m; h0++) {
            dp[0][h0] = 0;
        }

        for (int col = 1; col < n; col++) {
            int mid = col - 1; 
            long[][] ndp = new long[m][m];
            for (int i = 0; i < m; i++) Arrays.fill(ndp[i], NEG);

            for (int b = 0; b < m; b++) {
                long[] prefBest = new long[m];
                prefBest[0] = dp[0][b];
                for (int a = 1; a < m; a++) {
                    prefBest[a] = Math.max(prefBest[a - 1], dp[a][b]);
                }

                long[] suffBest = new long[m + 1];
                suffBest[m] = NEG;
                for (int a = m - 1; a >= 0; a--) {
                    long val = dp[a][b];
                    if (val > NEG / 2) {
                        val += Math.max(0L, pref[mid][a] - pref[mid][b]);
                    }
                    suffBest[a] = Math.max(suffBest[a + 1], val);
                }

                for (int c = 0; c < m; c++) {
                    long best = suffBest[c + 1]; 

                    if (prefBest[c] > NEG / 2) { 
                        long add = Math.max(0L, pref[mid][c] - pref[mid][b]);
                        best = Math.max(best, prefBest[c] + add);
                    }

                    ndp[b][c] = best;
                }
            }

            dp = ndp;
        }
        long ans = 0;
        int last = n - 1;
        for (int a = 0; a < m; a++) {
            for (int b = 0; b < m; b++) {
                if (dp[a][b] > NEG / 2) {
                    long add = Math.max(0L, pref[last][a] - pref[last][b]);
                    ans = Math.max(ans, dp[a][b] + add);
                }
            }
        }

        return ans;
    }
}