class Solution {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        Arrays.sort(nums1);
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }
        Arrays.sort(order, (a, b) -> Integer.compare(nums2[a], nums2[b]));
        
        int[] result = new int[n];
        int left = 0;      
        int right = n - 1;   
        for (int x : nums1) {
            if (x > nums2[order[left]]) {
                result[order[left]] = x;
                left++;
            } else {
                result[order[right]] = x;
                right--;
            }
        }
        
        return result;
    }
}