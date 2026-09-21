class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] count = new long[k];

        for (int j = 0; j < nums.length; j++) {
            int v = nums[j] % k;
            long[] newCount = new long[k];
            for (int r = 0; r < k; r++) {
                newCount[(r * v) % k] += count[r];
            }
            newCount[v]++;
            count = newCount;
            for (int r = 0; r < k; r++) {
                result[r] += count[r];
            }
        }

        return result;
    }
}