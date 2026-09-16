class Solution {
    private static final int MOD = 1_000_000_007;
    public int numberOfSets(int n, int k) {
        int[] dp = new int[k + 1];
        dp[0] = 1;
        int[] sum = new int[k + 1];
        sum[1] = 1; 
        for (int i = 1; i < n; i++) {
            int[] nextDp = new int[k + 1];
            nextDp[0] = 1;
            for (int j = 1; j <= k; j++) {
                nextDp[j] = (dp[j] + sum[j]) % MOD;
            }
            for (int j = 1; j <= k; j++) {
                sum[j] = (sum[j] + nextDp[j - 1]) % MOD;
            }
            dp = nextDp;
        }
        return dp[k];
    }
}