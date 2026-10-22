class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return 0;
        }
        int idx0 = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                idx0 = i;
                break;
            }
        }
        boolean isInc = true;
        for (int j = 0; j < n; j++) {
            if (nums[(idx0 + j) % n] != j) {
                isInc = false;
                break;
            }
        }
        boolean isDec = true;
        for (int j = 0; j < n; j++) {
            if (nums[(idx0 + j) % n] != (n - j) % n) {
                isDec = false;
                break;
            }
        }
        if (!isInc && !isDec) {
            return -1;
        }

        int minOps = Integer.MAX_VALUE;
        if (isInc) {
            int k = idx0;
            int ops = (k == 0) ? 0 : Math.min(k, n - k + 2);
            minOps = Math.min(minOps, ops);
        }
        if (isDec) {
            int k = idx0;
            int ops = Math.min(n - k, ((k + 1) % n) + 1);
            minOps = Math.min(minOps, ops);
        }

        return minOps;
    }
}