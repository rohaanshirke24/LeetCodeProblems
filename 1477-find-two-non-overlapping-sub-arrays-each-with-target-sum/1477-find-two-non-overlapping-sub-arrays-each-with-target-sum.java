class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] dp = new int[n + 1];
        int INF = Integer.MAX_VALUE;
        for (int i = 0; i <= n; i++) {
            dp[i] = INF;
        }
        
        int ans = INF;
        int currentSum = 0;
        int left = 0;
        
        for (int right = 0; right < n; right++) {
            currentSum += arr[right];
            while (currentSum > target && left <= right) {
                currentSum -= arr[left];
                left++;
            }
            dp[right + 1] = dp[right];
            if (currentSum == target) {
                int curLen = right - left + 1;
                if (dp[left] != INF) {
                    ans = Math.min(ans, dp[left] + curLen);
                }
                dp[right + 1] = Math.min(dp[right + 1], curLen);
            }
        }
        
        return ans == INF ? -1 : ans;
    }
}