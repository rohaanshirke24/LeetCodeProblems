class Solution {
    private static final long MOD = 1_000_000_007L;
    private long[][] comb;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        comb = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            comb[i][0] = 1;
            comb[i][i] = 1;
            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int x : nums) {
            list.add(x);
        }

        long totalWays = dfs(list);
        long ans = (totalWays - 1 + MOD) % MOD;
        return (int) ans;
    }

    private long dfs(List<Integer> arr) {
        int m = arr.size();
        if (m <= 2) {
            return 1L;
        }

        int root = arr.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < m; i++) {
            int val = arr.get(i);
            if (val < root) {
                left.add(val);
            } else {
                right.add(val);
            }
        }

        long leftWays = dfs(left);
        long rightWays = dfs(right);

        int L = left.size();
        int R = right.size();
        long waysToMerge = comb[L + R][L];

        return (((waysToMerge * leftWays) % MOD) * rightWays) % MOD;
    }
}
