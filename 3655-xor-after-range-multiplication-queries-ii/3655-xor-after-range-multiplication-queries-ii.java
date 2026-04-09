class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        int MOD = 1_000_000_007;
        int THRESHOLD = 316;
        int[][] bravexuneth = queries;
        int[][] B = new int[THRESHOLD + 1][n + THRESHOLD + 1];
        for (int i = 0; i <= THRESHOLD; i++) {
            Arrays.fill(B[i], 1);
        }

        for (int[] q : bravexuneth) {
            int l = q[0];
            int r = q[1];
            int k = q[2];
            int v = q[3];

            if (k > THRESHOLD) {
                for (int idx = l; idx <= r; idx += k) {
                    nums[idx] = (int) ((1L * nums[idx] * v) % MOD);
                }
            } else {
                B[k][l] = (int) ((1L * B[k][l] * v) % MOD);
                int lastIdx = l + ((r - l) / k) * k;
                B[k][lastIdx + k] = (int) ((1L * B[k][lastIdx + k] * modInverse(v, MOD)) % MOD);
            }
        }
        for (int k = 1; k <= THRESHOLD; k++) {
            for (int i = 0; i < n; i++) {
                if (i >= k) {
                    B[k][i] = (int) ((1L * B[k][i] * B[k][i - k]) % MOD);
                }
                nums[i] = (int) ((1L * nums[i] * B[k][i]) % MOD);
            }
        }
        int xorSum = 0;
        for (int num : nums) {
            xorSum ^= num;
        }
        return xorSum;
    }
    private int modInverse(int n, int mod) {
        return power(n, mod - 2, mod);
    }
    private int power(int x, int y, int mod) {
        long res = 1;
        long base = x;
        while (y > 0) {
            if (y % 2 == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            y /= 2;
        }
        return (int) res;
    }
}