class Solution {
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        int maxPairSum = Integer.MIN_VALUE;
        // Pair the elements from the beginning and end of the sorted array
        for(int i=0; i<n/2; i++){
            int pairSum = nums[i]+nums[n-1-i];
            maxPairSum = Math.max(maxPairSum, pairSum);
        }
        return maxPairSum;
    }
}