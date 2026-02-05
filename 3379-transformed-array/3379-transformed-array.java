class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        // For each index in the array
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                // If the number is 0, keep it as is
                result[i] = nums[i];
            } else {
                // Calculate steps and direction
                int steps = Math.abs(nums[i]);
                int finalIndex;

                if (nums[i] > 0) {
                    // Move right
                    finalIndex = (i + steps) % n;
                } else {
                    // Move left
                    // Add n to handle negative numbers
                    finalIndex = ((i - steps) % n + n) % n;
                }

                // Store the value at the final position
                result[i] = nums[finalIndex];
            }
        }

        return result;
    }
}