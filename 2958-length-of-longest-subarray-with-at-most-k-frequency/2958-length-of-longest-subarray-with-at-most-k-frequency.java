class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        int exceedingCount = 0; 
        
        for (int right = 0; right < nums.length; right++) {
            int num = nums[right];
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
            
            if (frequencyMap.get(num) == k + 1) {
                exceedingCount++;
            }
            
            while (exceedingCount > 0) {
                int leftNum = nums[left];
                if (frequencyMap.get(leftNum) == k + 1) {
                    exceedingCount--;
                }
                frequencyMap.put(leftNum, frequencyMap.get(leftNum) - 1);
                if (frequencyMap.get(leftNum) == 0) {
                    frequencyMap.remove(leftNum);
                }
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}