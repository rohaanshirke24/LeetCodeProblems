class Solution {
    public int findValidSplit(int[] nums) {
        Map<Integer, Integer> first = new HashMap<>();
        int n = nums.length;
        int[] last = new int[n];
        
        for (int i = 0; i < n; i++) {
            last[i] = i;
        }

        for (int i = 0; i < n; i++) {
            int x = nums[i];

            for (int p = 2; p <= x / p; p++) {
                if (x % p == 0) {
                    if (first.containsKey(p)) {
                        last[first.get(p)] = i;
                    } else {
                        first.put(p, i);
                    }

                    while (x % p == 0) {
                        x /= p;
                    }
                }
            }

            if (x > 1) {
                if (first.containsKey(x)) {
                    last[first.get(x)] = i;
                } else {
                    first.put(x, i);
                }
            }
        }

        int mx = last[0];
        for (int i = 1; i < n; i++) {
            if (i > mx) {
                return i - 1;
            }
            mx = Math.max(mx, last[i]);
        }

        return -1;
    }
}