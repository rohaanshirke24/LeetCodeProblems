class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge((long) x, 1, Integer::sum);
        }
        int ans = 0;
        if (cnt.containsKey(1L)) {
            int ones = cnt.get(1L);
            ans = (ones % 2 == 1) ? ones : ones - 1;
            cnt.remove(1L);
        }
        for (long start : cnt.keySet()) {
            long x = start;
            int len = 0;

            while (cnt.getOrDefault(x, 0) > 1) {
                len += 2;
                x = x * x;
            }

            if (cnt.getOrDefault(x, 0) == 1) {
                len += 1;
            } else {
                len -= 1;
            }

            ans = Math.max(ans, len);
        }
        return ans;
    }
}