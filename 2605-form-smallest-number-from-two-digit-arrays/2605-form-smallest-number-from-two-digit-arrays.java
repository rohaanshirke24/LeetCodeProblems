class Solution {
    public int minNumber(int[] nums1, int[] nums2) {
        int minCommon = 10;
        
        for (int a : nums1) {
            for (int b : nums2) {
                if (a == b) {
                    minCommon = Math.min(minCommon, a);
                }
            }
        }
        
        if (minCommon != 10) {
            return minCommon;
        }
        
        int min1 = 10, min2 = 10;
        
        for (int x : nums1) {
            min1 = Math.min(min1, x);
        }
        
        for (int x : nums2) {
            min2 = Math.min(min2, x);
        }
        
        return Math.min(min1 * 10 + min2, min2 * 10 + min1);
    }
}