class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int totalSum = 0;
        for(int num : nums){
            totalSum += num;
        }
        int targetSum = totalSum - x;
        if(targetSum < 0){
            return -1; 
        }
        int left = 0;
        int minOps = Integer.MAX_VALUE;
        int currentSum = 0;
        for(int right = 0; right < n; right++){
            currentSum += nums[right];
            while(currentSum > targetSum){
                currentSum -= nums[left];
                left++;
            }
            if(currentSum == targetSum){
                int ops = n -(right - left + 1);
                minOps = Math.min(minOps, ops);
            }
        }
        return minOps == Integer.MAX_VALUE  ? -1 : minOps;
    }
}