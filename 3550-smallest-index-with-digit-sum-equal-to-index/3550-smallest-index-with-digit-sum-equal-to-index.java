class Solution {
    public int smallestIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int n = nums[i]; n > 0; n /= 10)
                sum += n % 10;
            if (sum == i) return i;
        }
        return -1;
    }
}