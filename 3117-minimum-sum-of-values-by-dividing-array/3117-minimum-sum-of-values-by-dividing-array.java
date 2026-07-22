class Solution {
    private static final int INF = 1_000_000_000;
    private static final int FULL_MASK = (1 << 17) - 1; 
    public int minimumValueSum(int[] nums, int[] andValues) {
        int n = nums.length;
        int m = andValues.length;
        Map<Integer, Integer>[][] mem = new Map[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mem[i][j] = new HashMap<>();
            }
        }
        int ans = dp(nums, andValues, 0, 0, FULL_MASK, mem);
        return ans >= INF ? -1 : ans;
    }
    private int dp(int[] nums, int[] andValues, int i, int j, int mask, Map<Integer, Integer>[][] mem) {
        int n = nums.length;
        int m = andValues.length;
        if (i == n && j == m) {
            return 0; 
        }
        if (i == n || j == m) {
            return INF; 
        }

        if (mem[i][j].containsKey(mask)) {
            return mem[i][j].get(mask);
        }
        mask &= nums[i];
        if (mask < andValues[j]) {
            mem[i][j].put(mask, INF);
            return INF;
        }

        int res;
        if (mask == andValues[j]) {
            int keepGoing = dp(nums, andValues, i + 1, j, mask, mem);

            int closeHere = nums[i] + dp(nums, andValues, i + 1, j + 1, FULL_MASK, mem);

            res = Math.min(keepGoing, closeHere);
        } else {
            res = dp(nums, andValues, i + 1, j, mask, mem);
        }

        mem[i][j].put(mask, res);
        return res;
    }
}