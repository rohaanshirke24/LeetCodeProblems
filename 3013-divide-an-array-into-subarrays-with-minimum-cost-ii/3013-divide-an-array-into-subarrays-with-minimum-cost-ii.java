class Solution {
    private TreeMap<Integer, Integer> small;
    private TreeMap<Integer, Integer> large;
    private long smallSum;
    private int smallSize;
    private int k_needed;

    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;
        k_needed = k - 2;
        small = new TreeMap<>();
        large = new TreeMap<>();
        smallSum = 0;
        smallSize = 0;
        
        // Initial window setup for i = 1
        // The candidates for the remaining k-2 starts are in nums[2 ... 1+dist]
        for (int j = 2; j <= Math.min(n - 1, 1 + dist); j++) {
            add(nums[j]);
        }
        
        long minCost = Long.MAX_VALUE;
        
        // Iterate over all possible start indices for the 2nd subarray
        for (int i = 1; i <= n - (k - 1); i++) {
            // Current cost = nums[0] (1st start) + nums[i] (2nd start) + sum of best (k-2) matches
            long currentCost = nums[0] + (long)nums[i] + smallSum;
            minCost = Math.min(minCost, currentCost);
            
            // Prepare window for the next iteration (i+1)
            if (i < n - (k - 1)) {
                // nums[i+1] leaves the candidate pool (it becomes the new 2nd start)
                remove(nums[i + 1]);
                
                // nums[i+1+dist] enters the candidate pool
                if (i + 1 + dist < n) {
                    add(nums[i + 1 + dist]);
                }
            }
        }
        
        return minCost;
    }
    
    // Adds a value to the data structure
    private void add(int val) {
        large.merge(val, 1, Integer::sum);
        
        // Move smallest from large to small to maintain order
        int firstLarge = large.firstKey();
        updateMap(large, firstLarge, -1);
        updateMap(small, firstLarge, 1);
        smallSum += firstLarge;
        smallSize++;
        
        // If small has too many elements, move the largest back to large
        if (smallSize > k_needed) {
            int lastSmall = small.lastKey();
            updateMap(small, lastSmall, -1);
            smallSum -= lastSmall;
            smallSize--;
            large.merge(lastSmall, 1, Integer::sum);
        }
    }
    
    // Removes a value from the data structure
    private void remove(int val) {
        if (large.containsKey(val)) {
            updateMap(large, val, -1);
        } else {
            // If not in large, it must be in small
            updateMap(small, val, -1);
            smallSum -= val;
            smallSize--;
        }
        
        // Refill small if it drops below needed size and we have candidates in large
        if (smallSize < k_needed && !large.isEmpty()) {
            int firstLarge = large.firstKey();
            updateMap(large, firstLarge, -1);
            updateMap(small, firstLarge, 1);
            smallSum += firstLarge;
            smallSize++;
        }
    }
    
    // Helper to update frequency map
    private void updateMap(TreeMap<Integer, Integer> map, int key, int delta) {
        int count = map.getOrDefault(key, 0) + delta;
        if (count == 0) {
            map.remove(key);
        } else {
            map.put(key, count);
        }
    }
}
