class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        if (nums.length <= 1)
            return nums.length;

        int maxLen = 1;
        int currentInc = 1;
        int currentDec = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                currentInc = currentInc + 1;
                currentDec = 1;
            } else if (nums[i] < nums[i - 1]) {
                currentDec = currentDec + 1;
                currentInc = 1;
            } else {
                currentInc = 1;
                currentDec = 1;
            }
            maxLen = Math.max(maxLen, Math.max(currentInc, currentDec));
        }

        return maxLen;
    }
}