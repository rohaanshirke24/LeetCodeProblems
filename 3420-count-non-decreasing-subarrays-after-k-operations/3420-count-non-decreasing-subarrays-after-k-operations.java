class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long[] pref = new long[n + 1];
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + nums[i];
        }
        int[] parent = new int[n];
        int[] stack = new int[n];
        int top = 0;
        for (int i = n - 1; i >= 0; i--) {
            while (top > 0 && nums[stack[top - 1]] <= nums[i]) {
                top--;
            }
            parent[i] = (top == 0) ? n : stack[top - 1];
            stack[top++] = i;
        }
        int LOG = 18;
        int[][] up = new int[n + 1][LOG];
        long[][] weight = new long[n + 1][LOG];
        long INF = 1_000_000_000_000_000_000L;
        for (int i = 0; i < n; i++) {
            int p = parent[i];
            up[i][0] = p;
            weight[i][0] = (long) (p - i) * nums[i] - (pref[p] - pref[i]);
        }
        up[n][0] = n;
        weight[n][0] = INF;
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i <= n; i++) {
                int p = up[i][j - 1];
                up[i][j] = up[p][j - 1];
                long w = weight[i][j - 1] + weight[p][j - 1];
                weight[i][j] = (w > INF) ? INF : w;
            }
        }
        long totalSubarrays = 0;
        
        for (int l = 0; l < n; l++) {
            int u = l;
            long rem = k;
            for (int j = LOG - 1; j >= 0; j--) {
                if (weight[u][j] <= rem) {
                    rem -= weight[u][j];
                    u = up[u][j];
                }
            }
            
            if (u == n) {
                totalSubarrays += (long) (n - l);
            } else {
                int low = u, high = parent[u] - 1;
                int bestR = u;
                
                while (low <= high) {
                    int mid = (low + high) >>> 1;
                    long cost = (long) (mid - u + 1) * nums[u] - (pref[mid + 1] - pref[u]);
                    if (cost <= rem) {
                        bestR = mid;
                        low = mid + 1; 
                    } else {
                        high = mid - 1;
                    }
                }
                
                totalSubarrays += (long) (bestR - l + 1);
            }
        }
        
        return totalSubarrays;
    }
}