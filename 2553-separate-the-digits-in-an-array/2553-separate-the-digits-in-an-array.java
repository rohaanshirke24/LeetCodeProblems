class Solution {
    public int[] separateDigits(int[] nums) {
        List<Integer> res = new ArrayList<>();
        
        for (int num : nums) {
            List<Integer> digits = new ArrayList<>();
            
            while (num > 0) {
                digits.add(num % 10);
                num /= 10;
            }
            
            Collections.reverse(digits);
            res.addAll(digits);
        }
        
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        
        return ans;
    }
}