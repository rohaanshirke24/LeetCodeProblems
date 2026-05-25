class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        boolean[] dp = new boolean[n];
        int[] pre = new int[n + 1];

        dp[0] = true;
        pre[1] = 1;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == '0') {
                int l = Math.max(0, i - maxJump);
                int r = i - minJump;

                if (l <= r && pre[r + 1] - pre[l] > 0) {
                    dp[i] = true;
                }
            }
            pre[i + 1] = pre[i] + (dp[i] ? 1 : 0);
        }

        return dp[n - 1];
    }
}