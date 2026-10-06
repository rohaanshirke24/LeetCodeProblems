class Solution {
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();
        long[] prefix = new long[n];
        long[] suffix = new long[n];
        int[] stack = new int[n];
        int top = -1;
        for (int i = 0; i < n; i++) {
            int curHeight = maxHeights.get(i);
            while (top >= 0 && maxHeights.get(stack[top]) >= curHeight) {
                top--;
            }
            if (top == -1) {
                prefix[i] = (long) curHeight * (i + 1);
            } else {
                int prevSmaller = stack[top];
                prefix[i] = prefix[prevSmaller] + (long) curHeight * (i - prevSmaller);
            }
            stack[++top] = i;
        }
        top = -1; 
        for (int i = n - 1; i >= 0; i--) {
            int curHeight = maxHeights.get(i);
            while (top >= 0 && maxHeights.get(stack[top]) >= curHeight) {
                top--;
            }
            if (top == -1) {
                suffix[i] = (long) curHeight * (n - i);
            } else {
                int nextSmaller = stack[top];
                suffix[i] = suffix[nextSmaller] + (long) curHeight * (nextSmaller - i);
            }
            stack[++top] = i;
        }
        long maxSum = 0;
        for (int i = 0; i < n; i++) {
            long totalAtPeak = prefix[i] + suffix[i] - maxHeights.get(i);
            maxSum = Math.max(maxSum, totalAtPeak);
        }
        
        return maxSum;
    }
}