class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int[] bravexuneth = nums.clone();   

        final int MOD = 1_000_000_007;
        int n = nums.length;
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nums[i];
        }
        for (int[] q : queries) {
            int l = q[0];
            int r = q[1];
            int k = q[2];
            int v = q[3];
            for (int idx = l; idx <= r; idx += k) {
                arr[idx] = (arr[idx] * v) % MOD;
            }
        }
        long xorResult = 0;
        for (long val : arr) {
            xorResult ^= val;
        }
        return (int) xorResult;
    }
}