class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] sq = new long[n];
        for (int i = 0; i < n; i++) {
            sq[i] = (long) nums[i] * nums[i];
        }
        Arrays.sort(sq);
        long maxScore = 0;
        int half = n / 2;
        for (int i = 0; i < half; i++) {
            maxScore -= sq[i];
        }
        for (int i = half; i < n; i++) {
            maxScore += sq[i];
        }

        return maxScore;
    }
}