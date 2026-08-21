class Solution {
    public int findIntegers(int n) {
        int[] dp = new int[32];
        dp[0] = 1; 
        dp[1] = 2; 
        for (int i = 2; i < 32; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int count = 0;
        int previousBit = 0;

        for (int bit = 30; bit >= 0; bit--) {
            if ((n & (1 << bit)) != 0) {
                count += dp[bit];
                if (previousBit == 1) {
                    return count;
                }

                previousBit = 1;
            } else {
                previousBit = 0;
            }
        }
        return count + 1;
    }
}