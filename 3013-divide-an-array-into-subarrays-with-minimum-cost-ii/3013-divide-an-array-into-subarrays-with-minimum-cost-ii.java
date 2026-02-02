class Solution {
    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;
        long result = Long.MAX_VALUE;
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();
        
        long leftSum = 0;
        int leftSize = 0;
        for (int secondStart = 1; secondStart < n; secondStart++) {
            left.clear();
            right.clear();
            leftSum = 0;
            leftSize = 0;
            int windowStart = secondStart + 1;
            int windowEnd = Math.min(n - 1, secondStart + dist);
            if (windowEnd - windowStart + 1 < k - 2) {
                continue;
            }
            for (int i = windowStart; i <= windowEnd; i++) {
                addToMap(right, nums[i]);
            }
            
            while (leftSize < k - 2 && !right.isEmpty()) {
                int smallest = right.firstKey();
                removeFromMap(right, smallest);
                addToMap(left, smallest);
                leftSum += smallest;
                leftSize++;
            }
            if (leftSize == k - 2) {
                result = Math.min(result, nums[secondStart] + leftSum);
            }
        }
        
        return nums[0] + (result == Long.MAX_VALUE ? 0 : result);
    }
    
    private void addToMap(TreeMap<Integer, Integer> map, int key) {
        map.put(key, map.getOrDefault(key, 0) + 1);
    }
    
    private void removeFromMap(TreeMap<Integer, Integer> map, int key) {
        int count = map.get(key);
        if (count == 1) {
            map.remove(key);
        } else {
            map.put(key, count - 1);
        }
    }
}