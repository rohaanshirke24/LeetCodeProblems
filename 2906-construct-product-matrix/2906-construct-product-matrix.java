class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int total = n * m;
        final int MOD = 12345;

        long[] prefix = new long[total + 1]; 
        long[] suffix = new long[total + 1]; 

        prefix[0] = 1;
        for (int k = 0; k < total; k++) {
            int i = k / m, j = k % m;
            prefix[k + 1] = (prefix[k] * grid[i][j]) % MOD;
        }

        suffix[total] = 1;
        for (int k = total - 1; k >= 0; k--) {
            int i = k / m, j = k % m;
            suffix[k] = (suffix[k + 1] * grid[i][j]) % MOD;
        }

        int[][] p = new int[n][m];
        for (int k = 0; k < total; k++) {
            int i = k / m, j = k % m;
            p[i][j] = (int)((prefix[k] * suffix[k + 1]) % MOD);
        }

        return p;
    }
}
