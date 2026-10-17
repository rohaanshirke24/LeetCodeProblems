class Solution {
    static class Element {
        int val;
        int sum;
        int originalIdx;

        Element(int val, int sum, int originalIdx) {
            this.val = val;
            this.sum = sum;
            this.originalIdx = originalIdx;
        }
    }

    public int minSwaps(int[] nums) {
        int n = nums.length;
        Element[] arr = new Element[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Element(nums[i], getDigitSum(nums[i]), i);
        }

        Arrays.sort(arr, (a, b) -> {
            if (a.sum != b.sum) {
                return Integer.compare(a.sum, b.sum);
            }
            return Integer.compare(a.val, b.val);
        });

        boolean[] visited = new boolean[n];
        int cycles = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                cycles++;
                int curr = i;
                while (!visited[curr]) {
                    visited[curr] = true;
                    curr = arr[curr].originalIdx;
                }
            }
        }

        return n - cycles;
    }

    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}