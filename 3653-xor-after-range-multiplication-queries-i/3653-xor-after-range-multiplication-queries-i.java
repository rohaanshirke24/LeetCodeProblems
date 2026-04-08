class Solution {
    private static final int MOD = 1_000_000_007;

    public int xorAfterQueries(int[] nums, int[][] queries) {
        for (int[] q : queries) {
            int li = q[0];
            int ri = q[1];
            int ki = q[2];
            int vi = q[3];

            for (int idx = li; idx <= ri; idx += ki) {
                nums[idx] = (int) ((1L * nums[idx] * vi) % MOD);
            }
        }

        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }
}