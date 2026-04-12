class Solution {
    public int minimumDistance(String word) {
        int n = word.length();
        int[][] dp = new int[n][26];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        }
        for (int a = 0; a < 26; a++) {
            dp[0][a] = 0;
        }
        
        for (int i = 0; i < n - 1; i++) {
            int c1 = word.charAt(i) - 'A';
            int c2 = word.charAt(i + 1) - 'A';
            for (int a = 0; a < 26; a++) {
                int cost1 = dist(c1, c2);
                if (dp[i][a] + cost1 < dp[i + 1][a]) {
                    dp[i + 1][a] = dp[i][a] + cost1;
                }
                
                int cost2 = dist(a, c2);
                if (dp[i][a] + cost2 < dp[i + 1][c1]) {
                    dp[i + 1][c1] = dp[i][a] + cost2;
                }
            }
        }
        
        int ans = Integer.MAX_VALUE;
        for (int a = 0; a < 26; a++) {
            ans = Math.min(ans, dp[n - 1][a]);
        }
        return ans;
    }
    
    private int dist(int a, int b) {
        int x1 = a / 6, y1 = a % 6;
        int x2 = b / 6, y2 = b % 6;
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}