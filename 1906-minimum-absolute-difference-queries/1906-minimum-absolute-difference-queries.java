class Solution {
    public int[] minDifference(int[] nums, int[][] queries) {
        int n = nums.length;
        int[][] prefix = new int[n + 1][101];

        for (int i = 0; i < n; i++) {
            for (int v = 1; v <= 100; v++) {
                prefix[i + 1][v] = prefix[i][v];
            }
            prefix[i + 1][nums[i]]++;
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1] + 1;

            int prev = -1;
            int best = Integer.MAX_VALUE;

            for (int v = 1; v <= 100; v++) {
                if (prefix[r][v] - prefix[l][v] > 0) {
                    if (prev != -1) {
                        best = Math.min(best, v - prev);
                    }
                    prev = v;
                }
            }

            ans[i] = (best == Integer.MAX_VALUE) ? -1 : best;
        }

        return ans;
    }
}