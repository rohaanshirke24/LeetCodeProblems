class Solution {
    private static final int MOD = 1_000_000_007;

    public int countKSubsequencesWithMaxBeauty(String s, int k) {
        if (k > 26) {
            return 0;
        }
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        int distinct = 0;
        for (int c : count) {
            if (c > 0) {
                distinct++;
            }
        }
        if (distinct < k) {
            return 0;
        }
        int[] freq = new int[distinct];
        int idx = 0;
        for (int c : count) {
            if (c > 0) {
                freq[idx++] = c;
            }
        }
        Arrays.sort(freq);
        int threshold = freq[distinct - k];

        int countGreater = 0;
        int countEqual = 0;
        for (int f : freq) {
            if (f > threshold) {
                countGreater++;
            } else if (f == threshold) {
                countEqual++;
            }
        }
        int needed = k - countGreater;

        long ans = 1;
        for (int f : freq) {
            if (f > threshold) {
                ans = (ans * f) % MOD;
            }
        }
        ans = (ans * nCr(countEqual, needed)) % MOD;
        ans = (ans * power(threshold, needed)) % MOD;

        return (int) ans;
    }
    private long nCr(int n, int r) {
        if (r < 0 || r > n) return 0;
        long[][] dp = new long[n + 1][r + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= Math.min(i, r); j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % MOD;
            }
        }
        return dp[n][r];
    }
    private long power(long base, int exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = (res * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }
}