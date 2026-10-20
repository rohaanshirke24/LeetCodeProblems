class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        List<Integer> divisors = new ArrayList<>();
        for (int d = 1; d * d <= n; d++) {
            if (n % d == 0) {
                divisors.add(d);
                if (d * d != n) {
                    divisors.add(n / d);
                }
            }
        }
        
        int totalSum = 0;
        for (int k : divisors) {
            if (isSortable(nums, n, k)) {
                totalSum += k;
            }
        }
        return totalSum;
    }
    
    private boolean isSortable(int[] nums, int n, int k) {
        int prevMax = Integer.MIN_VALUE;
        for (int i = 0; i < n; i += k) {
            int curMin = nums[i];
            int curMax = nums[i];
            int drops = 0;
            for (int j = i; j < i + k - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    drops++;
                }
                if (nums[j + 1] < curMin) curMin = nums[j + 1];
                if (nums[j + 1] > curMax) curMax = nums[j + 1];
            }
            if (k > 1 && nums[i + k - 1] > nums[i]) {
                drops++;
            }
            if (drops > 1) {
                return false;
            }
            if (i > 0 && prevMax > curMin) {
                return false;
            }
            
            prevMax = curMax;
        }
        
        return true;
    }
}