class Solution {
    public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long[][] maxDP = new long[m][n];
        long[][] minDP = new long[m][n];
        final int MOD = 1000000007;
        
        maxDP[0][0] = minDP[0][0] = grid[0][0];
        
        for (int j = 1; j < n; j++) {
            maxDP[0][j] = minDP[0][j] = maxDP[0][j-1] * grid[0][j];
        }
        
        for (int i = 1; i < m; i++) {
            maxDP[i][0] = minDP[i][0] = maxDP[i-1][0] * grid[i][0];
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                long topMax = maxDP[i-1][j] * grid[i][j];
                long topMin = minDP[i-1][j] * grid[i][j];
                long leftMax = maxDP[i][j-1] * grid[i][j];
                long leftMin = minDP[i][j-1] * grid[i][j];
                
                maxDP[i][j] = Math.max(Math.max(topMax, topMin), Math.max(leftMax, leftMin));
                minDP[i][j] = Math.min(Math.min(topMax, topMin), Math.min(leftMax, leftMin));
            }
        }
        
        if (maxDP[m-1][n-1] >= 0) {
            return (int) (maxDP[m-1][n-1] % MOD);
        }
        return -1;
    }
}