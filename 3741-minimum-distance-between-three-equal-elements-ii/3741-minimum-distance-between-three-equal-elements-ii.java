class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        int[] last1 = new int[n + 1];
        int[] last2 = new int[n + 1]; 

        for (int i = 0; i <= n; i++) {
            last1[i] = -1;
            last2[i] = -1;
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int x = nums[i];

            if (last2[x] != -1) {
                ans = Math.min(ans, 2 * (i - last2[x]));
            }

            last2[x] = last1[x];
            last1[x] = i;
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}