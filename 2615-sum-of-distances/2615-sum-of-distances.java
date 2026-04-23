class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] ans = new long[n];
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        for (List<Integer> idx : map.values()) {
            int m = idx.size();
            long total = 0;

            for (int pos : idx) {
                total += pos;
            }

            long leftSum = 0;

            for (int i = 0; i < m; i++) {
                long cur = idx.get(i);

                long left = cur * i - leftSum;
                long right = (total - leftSum - cur) - cur * (m - i - 1);

                ans[idx.get(i)] = left + right;
                leftSum += cur;
            }
        }

        return ans;
    }
}