class Solution {
    private static final int MOD = (int)1e9 + 7;
    private long[][] comb;
    
    public int numOfWays(int[] nums) {
        int n = nums.length;
        comb = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                comb[i][j] = (comb[i-1][j-1] + comb[i-1][j]) % MOD;
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums) list.add(num);
        
        long totalWays = dfs(list);
        return (int)((totalWays - 1 + MOD) % MOD);
    }
    
    private long dfs(List<Integer> nums) {
        int m = nums.size();
        if (m <= 1) return 1;  
        
        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int i = 1; i < m; i++) {
            if (nums.get(i) < root) left.add(nums.get(i));
            else right.add(nums.get(i));
        }
        
        long leftWays = dfs(left);
        long rightWays = dfs(right);
        long ways = comb[m - 1][left.size()];
        ways = (ways * leftWays) % MOD;
        ways = (ways * rightWays) % MOD;
        
        return ways;
    }
}