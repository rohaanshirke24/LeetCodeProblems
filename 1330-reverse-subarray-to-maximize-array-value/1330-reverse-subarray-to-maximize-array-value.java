class Solution {
    public int maxValueAfterReverse(int[] nums) {
        int n = nums.length;
        int totalSum = 0;
        int maxGain = 0;
        
        int minMax = Integer.MAX_VALUE;
        int maxMin = Integer.MIN_VALUE;
        
        for (int i = 0; i < n - 1; i++) {
            int a = nums[i];
            int b = nums[i + 1];
            int diff = Math.abs(a - b);
            totalSum += diff;
            minMax = Math.min(minMax, Math.max(a, b));
            maxMin = Math.max(maxMin, Math.min(a, b));
            maxGain = Math.max(maxGain, Math.abs(nums[0] - b) - diff);
            maxGain = Math.max(maxGain, Math.abs(nums[n - 1] - a) - diff);
        }
        maxGain = Math.max(maxGain, 2 * (maxMin - minMax));
        
        return totalSum + maxGain;
    }
}