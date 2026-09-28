class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int[] best = new int[k];
        for (int i = Math.max(0, k - n); i <= Math.min(k, m); i++) {
            int[] sub1 = maxSubsequence(nums1, i);
            int[] sub2 = maxSubsequence(nums2, k - i);
            int[] candidate = merge(sub1, sub2);
            if (compare(candidate, 0, best, 0) > 0) {
                best = candidate;
            }
        }
        return best;
    }
    private int[] maxSubsequence(int[] nums, int t) {
        int n = nums.length;
        int[] stack = new int[t];
        int top = -1;
        int drop = n - t; 

        for (int num : nums) {
            while (top >= 0 && stack[top] < num && drop > 0) {
                top--;
                drop--;
            }
            if (top + 1 < t) {
                stack[++top] = num;
            } else {
                drop--; 
            }
        }
        return stack;
    }
    private int[] merge(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, idx = 0;

        while (i < a.length && j < b.length) {
            if (compare(a, i, b, j) >= 0) {
                result[idx++] = a[i++];
            } else {
                result[idx++] = b[j++];
            }
        }
        while (i < a.length) result[idx++] = a[i++];
        while (j < b.length) result[idx++] = b[j++];
        return result;
    }
    private int compare(int[] a, int i, int[] b, int j) {
        while (i < a.length && j < b.length) {
            if (a[i] != b[j]) return a[i] - b[j];
            i++;
            j++;
        }
        return (a.length - i) - (b.length - j); 
    }
}