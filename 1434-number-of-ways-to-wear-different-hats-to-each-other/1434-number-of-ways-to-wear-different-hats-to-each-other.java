class Solution {
    public int numberWays(List<List<Integer>> hats) {
        int n = hats.size();
        int MOD = 1_000_000_007;
        List<Integer>[] hatToPeople = new ArrayList[41];
        for (int h = 1; h <= 40; h++) {
            hatToPeople[h] = new ArrayList<>();
        }
        for (int person = 0; person < n; person++) {
            for (int hat : hats.get(person)) {
                hatToPeople[hat].add(person);
            }
        }
        int totalMasks = 1 << n;
        int[] dp = new int[totalMasks];
        dp[0] = 1; 
        for (int h = 1; h <= 40; h++) {
            if (hatToPeople[h].isEmpty()) {
                continue;
            }
            int[] nextDp = dp.clone();
            for (int person : hatToPeople[h]) {
                int personBit = 1 << person;
                for (int mask = 0; mask < totalMasks; mask++) {
                    if ((mask & personBit) == 0 && dp[mask] > 0) {
                        int newMask = mask | personBit;
                        nextDp[newMask] = (nextDp[newMask] + dp[mask]) % MOD;
                    }
                }
            }
            dp = nextDp;
        }
        return dp[totalMasks - 1];
    }
}