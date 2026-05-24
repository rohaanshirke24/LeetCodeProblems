class Solution {
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[] memo = new int[n];
        int ans = 1;

        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dfs(arr, d, i, memo));
        }

        return ans;
    }

    private int dfs(int[] arr, int d, int i, int[] memo) {
        if (memo[i] != 0) return memo[i];

        int best = 1;

        // left
        for (int j = i - 1; j >= Math.max(0, i - d); j--) {
            if (arr[j] >= arr[i]) break;
            best = Math.max(best, 1 + dfs(arr, d, j, memo));
        }

        // right
        for (int j = i + 1; j <= Math.min(arr.length - 1, i + d); j++) {
            if (arr[j] >= arr[i]) break;
            best = Math.max(best, 1 + dfs(arr, d, j, memo));
        }

        memo[i] = best;
        return best;
    }
}