class Solution {
    public int maxSumMinProduct(int[] nums) {
        int n = nums.length;
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        int[] left = new int[n];
        int[] stack = new int[n]; 
        int top = -1;
        
        for (int i = 0; i < n; i++) {
            while (top >= 0 && nums[stack[top]] >= nums[i]) {
                top--;
            }
            left[i] = (top >= 0) ? stack[top] + 1 : 0;
            stack[++top] = i;
        }
        int[] right = new int[n];
        top = -1;
        
        for (int i = n - 1; i >= 0; i--) {
            while (top >= 0 && nums[stack[top]] >= nums[i]) {
                top--;
            }
            right[i] = (top >= 0) ? stack[top] - 1 : n - 1;
            stack[++top] = i;
        }
        long maxProduct = 0;
        for (int i = 0; i < n; i++) {
            long subarraySum = prefixSum[right[i] + 1] - prefixSum[left[i]];
            long product = (long) nums[i] * subarraySum;
            if (product > maxProduct) {
                maxProduct = product;
            }
        }
        long mod = 1_000_000_007L;
        return (int) (maxProduct % mod);
    }
}