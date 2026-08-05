class Solution {
    public int connectTwoGroups(List<List<Integer>> cost) {
        int m = cost.size();
        int n = cost.get(0).size();
        int totalMasks = 1 << n;
        int INF = 1_000_000_000;
        int[] minCostToGroup1 = new int[n];
        Arrays.fill(minCostToGroup1, INF);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                minCostToGroup1[j] = Math.min(
                    minCostToGroup1[j],
                    cost.get(i).get(j)
                );
            }
        }
        int[] dp = new int[totalMasks];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 0; i < m; i++) {
            int[] nextDp = new int[totalMasks];
            Arrays.fill(nextDp, INF);

            for (int mask = 0; mask < totalMasks; mask++) {
                if (dp[mask] == INF) continue;
                for (int j = 0; j < n; j++) {
                    int nextMask = mask | (1 << j);

                    nextDp[nextMask] = Math.min(
                        nextDp[nextMask],
                        dp[mask] + cost.get(i).get(j)
                    );
                }
            }

            dp = nextDp;
        }

        int answer = INF;
        for (int mask = 0; mask < totalMasks; mask++) {
            int totalCost = dp[mask];

            for (int j = 0; j < n; j++) {
                if ((mask & (1 << j)) == 0) {
                    totalCost += minCostToGroup1[j];
                }
            }

            answer = Math.min(answer, totalCost);
        }

        return answer;
    }
}