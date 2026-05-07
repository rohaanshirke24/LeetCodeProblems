class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;
        int[] preMax = new int[n];
        int[] ans = new int[n];

        preMax[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preMax[i] = Math.max(preMax[i - 1], nums[i]);
        }

        int sufMin = Integer.MAX_VALUE;

        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1) {
                ans[i] = preMax[i];
            } else {
                ans[i] = (preMax[i] > sufMin) ? ans[i + 1] : preMax[i];
            }
            sufMin = Math.min(sufMin, nums[i]);
        }

        return ans;
    }
}