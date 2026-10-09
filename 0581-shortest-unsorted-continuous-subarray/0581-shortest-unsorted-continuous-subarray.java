class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int start = -1, end = -2; 
        
        int max = nums[0];
        int min = nums[n - 1];
        
        for (int i = 1; i < n; i++) {
            max = Math.max(max, nums[i]);
            if (nums[i] < max) {
                end = i;
            }
            
            int j = n - 1 - i;
            min = Math.min(min, nums[j]);
            if (nums[j] > min) {
                start = j;
            }
        }
        
        return end - start + 1;
    }
}