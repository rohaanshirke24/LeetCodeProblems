class Solution {
    public boolean canDistribute(int[] nums, int[] quantity) {
        Map<Integer, Integer> frequency = new HashMap<>();

        for (int num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }

        int customers = quantity.length;
        int fullMask = (1 << customers) - 1;
        int[] needed = new int[1 << customers];

        for (int mask = 1; mask <= fullMask; mask++) {
            int bit = mask & -mask;
            int customer = Integer.numberOfTrailingZeros(bit);
            needed[mask] = needed[mask ^ bit] + quantity[customer];
        }

        boolean[] dp = new boolean[1 << customers];
        dp[0] = true;

        for (int count : frequency.values()) {
            boolean[] next = dp.clone();

            for (int servedMask = 0; servedMask <= fullMask; servedMask++) {
                if (!dp[servedMask]) {
                    continue;
                }

                int remainingCustomers = fullMask ^ servedMask;
                for (int subset = remainingCustomers; subset > 0;
                     subset = (subset - 1) & remainingCustomers) {

                    if (needed[subset] <= count) {
                        next[servedMask | subset] = true;
                    }
                }
            }

            dp = next;

            if (dp[fullMask]) {
                return true;
            }
        }

        return dp[fullMask];
    }
}