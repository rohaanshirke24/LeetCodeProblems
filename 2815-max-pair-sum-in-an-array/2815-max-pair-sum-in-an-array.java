class Solution {
    public int maxSum(int[] nums) {
        int[] maxVal = new int[10];
        int ans = -1;

        for (int num : nums) {
            int d = getMaxDigit(num);
            if (maxVal[d] > 0) {
                ans = Math.max(ans, num + maxVal[d]);
            }

            maxVal[d] = Math.max(maxVal[d], num);
        }

        return ans;
    }
    private int getMaxDigit(int num) {
        int maxDigit = 0;
        while (num > 0) {
            maxDigit = Math.max(maxDigit, num % 10);
            num /= 10;
        }
        return maxDigit;
    }
}