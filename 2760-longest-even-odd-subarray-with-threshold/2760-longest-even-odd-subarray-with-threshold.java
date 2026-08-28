class Solution {
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int maxLen = 0;
        int cur = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > threshold) {
                cur = 0;
            } else if (cur > 0 && nums[i] % 2 != nums[i - 1] % 2) {
                cur++;
            } else {
                cur = (nums[i] % 2 == 0) ? 1 : 0;
            }

            maxLen = Math.max(maxLen, cur);
        }

        return maxLen;
    }
}