class Solution {
    public int largestInteger(int[] nums, int k) {
        int[] count = new int[51]; 

        for (int start = 0; start <= nums.length - k; start++) {
            Set<Integer> seenInWindow = new HashSet<>();

            for (int i = start; i < start + k; i++) {
                seenInWindow.add(nums[i]);
            }

            for (int num : seenInWindow) {
                count[num]++;
            }
        }

        int answer = -1;

        for (int num = 0; num <= 50; num++) {
            if (count[num] == 1) {
                answer = num;
            }
        }

        return answer;
    }
}