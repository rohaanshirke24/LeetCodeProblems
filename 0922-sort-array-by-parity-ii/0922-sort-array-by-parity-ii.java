class Solution {
    public int[] sortArrayByParityII(int[] nums) {
        int odd = 1;
        int n = nums.length;

        for (int even = 0; even < n; even += 2) {
            if ((nums[even] & 1) == 1) { 
                while ((nums[odd] & 1) == 1) { 
                    odd += 2;
                }
                int temp = nums[even];
                nums[even] = nums[odd];
                nums[odd] = temp;
            }
        }
        return nums;
    }
}
