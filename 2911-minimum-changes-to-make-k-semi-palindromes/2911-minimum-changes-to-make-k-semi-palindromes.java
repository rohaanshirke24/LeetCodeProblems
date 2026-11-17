class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        List<Integer>[] divisors = new List[n+1];
        for (int L = 2; L <= n; L++) {
            divisors[L] = new ArrayList<>();
            for (int d = 1; d < L; d++) {
                if (L % d == 0) {
                    divisors[L].add(d);
                }
            }
        }

        int[][] costMat = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(costMat[i], Integer.MAX_VALUE);
        }
        for (int L = 2; L <= n; L++) {
            for (int l = 0; l + L <= n; l++) {
                int r = l + L - 1;
                for (int d : divisors[L]) {
                    int totalCost = 0;
                    int m = L / d;
                    for (int i0 = 0; i0 < d; i0++) {
                        for (int j = 0; j < m / 2; j++) {
                            int leftIndex = l + i0 + j * d;
                            int rightIndex = l + i0 + (m - 1 - j) * d;
                            if (s.charAt(leftIndex) != s.charAt(rightIndex)) {
                                totalCost++;
                            }
                        }
                    }
                    if (totalCost < costMat[l][r]) {
                        costMat[l][r] = totalCost;
                    }
                }
            }
        }
        int[][] dp = new int[n+1][k+1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        for (int j = 1; j <= k; j++) {
            for (int i = 2 * j; i <= n; i++) {
                for (int t = 2 * (j-1); t <= i-2; t++) {
                    if (dp[t][j-1] != Integer.MAX_VALUE && costMat[t][i-1] != Integer.MAX_VALUE) {
                        long newCost = (long) dp[t][j-1] + costMat[t][i-1];
                        if (newCost < dp[i][j]) {
                            dp[i][j] = (int) newCost;
                        }
                    }
                }
            }
        }
        return dp[n][k];
    }
}