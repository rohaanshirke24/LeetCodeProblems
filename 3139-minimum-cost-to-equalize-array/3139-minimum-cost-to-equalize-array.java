class Solution {
    private static final int MOD = 1_000_000_007;

    public int minCostToEqualizeArray(int[] nums, int cost1, int cost2) {
        int n = nums.length;
        if (n <= 1) {
            return 0;
        }
        if (n == 2) {
            long diff = Math.abs(nums[0] - nums[1]);
            return (int) ((diff % MOD) * cost1 % MOD);
        }

        int max = nums[0];
        int min = nums[0];
        for (int x : nums) {
            if (x > max) max = x;
            if (x < min) min = x;
        }

        long sumDiff = 0;
        for (int x : nums) {
            sumDiff += (max - x);
        }
        if (2L * cost1 <= cost2) {
            return (int) ((sumDiff % MOD) * (cost1 % MOD) % MOD);
        }
        long diff = 2L * (max - min) - sumDiff;
        long T0 = max;
        if (diff > 0) {
            T0 = max + (diff + n - 3) / (n - 2); 
        }
        long[] candidates = {
            max, max + 1, max + 2,
            T0 - 2, T0 - 1, T0, T0 + 1, T0 + 2
        };

        long minCost = Long.MAX_VALUE;
        for (long T : candidates) {
            if (T < max) continue;
            long cost = computeCost(T, n, sumDiff, max, min, cost1, cost2);
            if (cost < minCost) {
                minCost = cost;
            }
        }

        return (int) (minCost % MOD);
    }

    private long computeCost(long T, int n, long sumDiff, int max, int min, long cost1, long cost2) {
        long S = sumDiff + (long) n * (T - max);
        long dMax = T - min;
        long rem = S - dMax;

        if (dMax > rem) {
            long pairs = rem;
            long singles = dMax - rem;
            return pairs * cost2 + singles * cost1;
        } else {
            long pairs = S / 2;
            long singles = S % 2;
            return pairs * cost2 + singles * cost1;
        }
    }
}