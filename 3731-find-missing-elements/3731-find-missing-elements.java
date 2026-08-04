class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        Set<Integer> present = new HashSet<>();
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for (int num : nums) {
            present.add(num);
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        
        List<Integer> missing = new ArrayList<>();
        
        for (int value = min + 1; value < max; value++) {
            if (!present.contains(value)) {
                missing.add(value);
            }
        }
        
        return missing;
    }
}