class Solution {
    private static final int MOD = 1_000_000_007;
    private static final int MAX = 200;

    public int subsequencePairCount(int[] nums) {
        long[][] dp = new long[MAX + 1][MAX + 1];
        dp[0][0] = 1;

        for (int x : nums) {
            long[][] next = new long[MAX + 1][MAX + 1];

            for (int g1 = 0; g1 <= MAX; g1++) {
                for (int g2 = 0; g2 <= MAX; g2++) {
                    long ways = dp[g1][g2];

                    if (ways == 0) {
                        continue;
                    }

                    next[g1][g2] = (next[g1][g2] + ways) % MOD;

                    int newG1 = gcd(g1, x);
                    next[newG1][g2] = (next[newG1][g2] + ways) % MOD;

                    int newG2 = gcd(g2, x);
                    next[g1][newG2] = (next[g1][newG2] + ways) % MOD;
                }
            }

            dp = next;
        }

        long answer = 0;

        for (int g = 1; g <= MAX; g++) {
            answer = (answer + dp[g][g]) % MOD;
        }

        return (int) answer;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}