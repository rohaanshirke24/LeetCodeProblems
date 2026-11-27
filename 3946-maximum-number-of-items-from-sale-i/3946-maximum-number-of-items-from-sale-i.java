class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxFactor = 0;
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }

        int[] freq = new int[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }

        int[] multipleCount = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            if (freq[f] == 0) continue;
            for (int m = f; m <= maxFactor; m += f) {
                multipleCount[f] += freq[m];
            }
        }

        int[] dp = new int[budget + 1];
        for (int i = 0; i < n; i++) {
            int factor = items[i][0];
            int price = items[i][1];
            int value = 1 + (multipleCount[factor] - 1);

            for (int w = budget; w >= price; w--) {
                dp[w] = Math.max(dp[w], dp[w - price] + value);
            }
        }

        int ans = 0;
        for (int w = 0; w <= budget; w++) {
            ans = Math.max(ans, dp[w] + (budget - w) / minPrice);
        }

        return ans;
    }
}