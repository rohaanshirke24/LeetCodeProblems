class Solution {
    public int maximumSetSize(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for (int x : nums1) set1.add(x);
        for (int x : nums2) set2.add(x);

        int n = nums1.length;
        int only1 = 0, only2 = 0, common = 0;

        for (int x : set1) {
            if (!set2.contains(x)) {
                only1++;
            }
        }

        for (int x : set2) {
            if (!set1.contains(x)) {
                only2++;
            } else {
                common++;
            }
        }

        only1 = Math.min(only1, n / 2);
        only2 = Math.min(only2, n / 2);

        return Math.min(only1 + only2 + common, n);
    }
}