class Solution {
    private static final int MOD = 1_000_000_007;

    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] destR = new int[m][n];
        int[][] destD = new int[m][n];
        int[][] dp = new int[m][n];
        for (int r = m - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                if (grid[r][c] == 0) {
                    destR[r][c] = r * n + c;
                    destD[r][c] = r * n + c;
                } else {
                    
                    destR[r][c] = (r + 1 < m) ? destD[r + 1][c] : -1;
                    destD[r][c] = (c + 1 < n) ? destR[r][c + 1] : -1;
                }
                if (r == m - 1 && c == n - 1) {
                    dp[r][c] = 1;
                } else if (grid[r][c] == 0) {
                    int ways = 0;
                    if (c + 1 < n) {
                        int land = destR[r][c + 1];
                        if (land != -1) {
                            ways += dp[land / n][land % n];
                            if (ways >= MOD) {
                                ways -= MOD;
                            }
                        }
                    }
                    if (r + 1 < m) {
                        int land = destD[r + 1][c];
                        if (land != -1) {
                            ways += dp[land / n][land % n];
                            if (ways >= MOD) {
                                ways -= MOD;
                            }
                        }
                    }

                    dp[r][c] = ways;
                }
            }
        }

        return dp[0][0];
    }
}