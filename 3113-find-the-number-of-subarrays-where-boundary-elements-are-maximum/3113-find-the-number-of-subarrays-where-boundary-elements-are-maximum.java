class Solution {
    public long numberOfSubarrays(int[] nums) {
        Deque<long[]> stack = new ArrayDeque<>();
        long result = 0;

        for (int num : nums) {
            while (!stack.isEmpty() && stack.peek()[0] < num) {
                stack.pop();
            }

            if (!stack.isEmpty() && stack.peek()[0] == num) {
                stack.peek()[1]++;
                result += stack.peek()[1];
            } else {
                stack.push(new long[]{num, 1});
                result += 1;
            }
        }

        return result;
    }
}