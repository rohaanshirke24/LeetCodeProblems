class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length, n = mat[0].length;

        // Use long to be safe with sums
        long[][] pre = new long[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                pre[i][j] = pre[i - 1][j] + pre[i][j - 1] - pre[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }

        int lo = 0, hi = Math.min(m, n);
        while (lo < hi) {
            int mid = lo + (hi - lo + 1) / 2;
            if (feasible(pre, m, n, mid, threshold)) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    private boolean feasible(long[][] pre, int m, int n, int k, int threshold) {
        if (k == 0) return true;
        for (int r = 0; r + k <= m; r++) {
            for (int c = 0; c + k <= n; c++) {
                long sum = pre[r + k][c + k] - pre[r][c + k] - pre[r + k][c] + pre[r][c];
                if (sum <= threshold) return true;
            }
        }
        return false;
    }
}
