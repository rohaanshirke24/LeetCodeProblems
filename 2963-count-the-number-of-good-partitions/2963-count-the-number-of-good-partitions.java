class Solution {
    public int numberOfGoodPartitions(int[] nums) {
        final int MOD = 1_000_000_007;

        Map<Integer, Integer> last = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            last.put(nums[i], i);
        }

        int segments = 0;
        int right = 0;

        for (int i = 0; i < nums.length; i++) {
            right = Math.max(right, last.get(nums[i]));
            if (i == right) {
                segments++;
            }
        }

        long ans = 1;
        for (int i = 1; i < segments; i++) {
            ans = (ans * 2) % MOD;
        }

        return (int) ans;
    }
}