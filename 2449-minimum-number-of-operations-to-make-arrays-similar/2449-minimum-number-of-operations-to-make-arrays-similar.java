class Solution {
    public long makeSimilar(int[] nums, int[] target) {
        int k1 = partitionEvenOdd(nums);
        int k2 = partitionEvenOdd(target);
        Arrays.sort(nums, 0, k1);
        Arrays.sort(target, 0, k2);
        Arrays.sort(nums, k1, nums.length);
        Arrays.sort(target, k2, target.length);
        long totalDiff = 0;
        for (int i = 0; i < nums.length; i++) {
            totalDiff += Math.abs(nums[i] - target[i]);
        }
        return totalDiff / 4;
    }
    private int partitionEvenOdd(int[] arr) {
        int i = 0;
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] % 2 == 0) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }
        return i; 
    }
}