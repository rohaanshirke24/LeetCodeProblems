class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return n;
        }

        int[] sa = buildSuffixArray(nums);
        int[] lcp = buildLcp(nums, sa);

        int ans = n;
        for (int k = 0; k < n; k++) {
            int prevLcp = (k > 0) ? lcp[k - 1] : 0;
            int nextLcp = lcp[k]; // lcp[n - 1] == 0
            int maxDup = Math.max(prevLcp, nextLcp);
            int sufLen = n - sa[k];
            if (maxDup < sufLen) {
                ans = Math.min(ans, maxDup + 1);
            }
        }
        return ans;
    }

    private int[] buildSuffixArray(int[] nums) {
        int n = nums.length;
        int[] sa = new int[n];
        int[] tmp = new int[n];
        int[] rk = new int[n];

        // 4-pass 8-bit LSD radix sort of indices by unsigned-shifted nums[i]
        for (int i = 0; i < n; i++) {
            sa[i] = i;
        }
        int[] radixCnt = new int[256];
        for (int shift = 0; shift < 32; shift += 8) {
            Arrays.fill(radixCnt, 0);
            for (int i = 0; i < n; i++) {
                int key = ((nums[sa[i]] ^ Integer.MIN_VALUE) >>> shift) & 0xFF;
                radixCnt[key]++;
            }
            for (int i = 1; i < 256; i++) {
                radixCnt[i] += radixCnt[i - 1];
            }
            for (int i = n - 1; i >= 0; i--) {
                int idx = sa[i];
                int key = ((nums[idx] ^ Integer.MIN_VALUE) >>> shift) & 0xFF;
                tmp[--radixCnt[key]] = idx;
            }
            int[] swap = sa;
            sa = tmp;
            tmp = swap;
        }

        rk[sa[0]] = 0;
        int classes = 1;
        for (int i = 1; i < n; i++) {
            if (nums[sa[i]] != nums[sa[i - 1]]) {
                classes++;
            }
            rk[sa[i]] = classes - 1;
        }

        int[] cnt = new int[n];
        for (int k = 1; classes < n; k <<= 1) {
            int p = 0;
            for (int i = n - k; i < n; i++) {
                tmp[p++] = i;
            }
            for (int i = 0; i < n; i++) {
                if (sa[i] >= k) {
                    tmp[p++] = sa[i] - k;
                }
            }

            Arrays.fill(cnt, 0, classes, 0);
            for (int i = 0; i < n; i++) {
                cnt[rk[tmp[i]]]++;
            }
            for (int i = 1; i < classes; i++) {
                cnt[i] += cnt[i - 1];
            }
            for (int i = n - 1; i >= 0; i--) {
                int idx = tmp[i];
                sa[--cnt[rk[idx]]] = idx;
            }

            tmp[sa[0]] = 0;
            classes = 1;
            for (int i = 1; i < n; i++) {
                int a = sa[i - 1], b = sa[i];
                int a2 = (a + k < n) ? rk[a + k] : -1;
                int b2 = (b + k < n) ? rk[b + k] : -1;
                if (rk[a] != rk[b] || a2 != b2) {
                    classes++;
                }
                tmp[b] = classes - 1;
            }
            int[] swap = rk;
            rk = tmp;
            tmp = swap;
        }
        return sa;
    }

    private int[] buildLcp(int[] nums, int[] sa) {
        int n = nums.length;
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[sa[i]] = i;
        }

        int[] lcp = new int[n];
        int h = 0;
        for (int i = 0; i < n; i++) {
            int r = rank[i];
            if (r == n - 1) {
                h = 0;
                continue;
            }
            int j = sa[r + 1];
            while (i + h < n && j + h < n && nums[i + h] == nums[j + h]) {
                h++;
            }
            lcp[r] = h;
            if (h > 0) {
                h--;
            }
        }
        return lcp;
    }
}