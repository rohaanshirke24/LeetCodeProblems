class Solution {
    public long maximumSumOfHeights(int[] heights) {
        int n = heights.length;
        long[] prefix = new long[n];
        long[] suffix = new long[n];
        int[] stack = new int[n];
        int top = -1;
        for (int i = 0; i < n; i++) {
            while (top >= 0 && heights[stack[top]] >= heights[i]) {
                top--;
            }
            if (top == -1) {
                prefix[i] = (long) heights[i] * (i + 1);
            } else {
                int prevSmaller = stack[top];
                prefix[i] = prefix[prevSmaller] + (long) heights[i] * (i - prevSmaller);
            }
            stack[++top] = i;
        }
        top = -1;
        for (int i = n - 1; i >= 0; i--) {
            while (top >= 0 && heights[stack[top]] >= heights[i]) {
                top--;
            }
            if (top == -1) {
                suffix[i] = (long) heights[i] * (n - i);
            } else {
                int nextSmaller = stack[top];
                suffix[i] = suffix[nextSmaller] + (long) heights[i] * (nextSmaller - i);
            }
            stack[++top] = i;
        }
        long maxSum = 0;
        for (int i = 0; i < n; i++) {
            long total = prefix[i] + suffix[i] - heights[i];
            maxSum = Math.max(maxSum, total);
        }

        return maxSum;
    }
}