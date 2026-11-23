class Solution {
    public int sumDistance(int[] nums, String s, int d) {
        int n = nums.length;
        long[] pos = new long[n];
        long MOD = 1_000_000_007L;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'R') {
                pos[i] = (long) nums[i] + d;
            } else {
                pos[i] = (long) nums[i] - d;
            }
        }
        Arrays.sort(pos);
        long totalDistance = 0;
        for (int i = 1; i < n; i++) {
            long gap = (pos[i] - pos[i - 1]) % MOD;
            long count = ((long) i * (n - i)) % MOD;
            totalDistance = (totalDistance + gap * count) % MOD;
        }

        return (int) totalDistance;
    }
}