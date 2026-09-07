class Solution {
    public int distinctSubseqII(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int[] last = new int[26];
        Arrays.fill(last, -1);
        int mod = (int) 1e9 + 7;
        for (int i = 0; i < n; i++) {
            int x = s.charAt(i) - 'a';
            dp[i + 1] = dp[i] * 2 % mod;
            if (last[x] >= 0) {
                dp[i + 1] -= dp[last[x]];
            }
            dp[i + 1] %= mod;
            last[x] = i;
        }
        dp[n]--;
        return (dp[n] + mod) % mod;
    }
}