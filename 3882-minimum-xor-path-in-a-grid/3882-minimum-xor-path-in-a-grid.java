class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        boolean[][] dp = new boolean[n][1024];
        dp[0][grid[0][0]] = true;
        for (int j = 1; j < n; j++) {
            int val = grid[0][j];
            for (int v = 0; v < 1024; v++) {
                if (dp[j - 1][v]) {
                    dp[j][v ^ val] = true;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            boolean[][] nextDp = new boolean[n][1024];
            for (int j = 0; j < n; j++) {
                int val = grid[i][j];
                for (int v = 0; v < 1024; v++) {
                    if (dp[j][v] || (j > 0 && nextDp[j - 1][v])) {
                        nextDp[j][v ^ val] = true;
                    }
                }
            }
            dp = nextDp;
        }
        
        for (int v = 0; v < 1024; v++) {
            if (dp[n - 1][v]) {
                return v;
            }
        }
        
        return 0;
    }
}