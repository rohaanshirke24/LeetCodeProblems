class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        int maxVal = 0;
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
        }
        int[] dp = new int[maxVal + 2];
        int maxConsecutive = 0;
        for (int num : nums) {
            dp[num + 1] = dp[num] + 1;
            dp[num] = dp[num - 1] + 1;
            maxConsecutive = Math.max(maxConsecutive, Math.max(dp[num + 1], dp[num]));
        }
        
        return maxConsecutive;
    }
}