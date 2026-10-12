import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        long MOD = 1_000_000_007L;
        int[] bitCount = new int[31];
        for (int num : nums) {
            for (int b = 0; b < 31; b++) {
                if (((num >> b) & 1) == 1) {
                    bitCount[b]++;
                }
            }
        }
        long totalSum = 0;
        for (int i = 0; i < k; i++) {
            long currentVal = 0;
            for (int b = 0; b < 31; b++) {
                if (bitCount[b] > 0) {
                    currentVal |= (1L << b);
                    bitCount[b]--;
                }
            }
            long squared = (currentVal % MOD) * (currentVal % MOD) % MOD;
            totalSum = (totalSum + squared) % MOD;
        }
        return (int) totalSum;
    }
}