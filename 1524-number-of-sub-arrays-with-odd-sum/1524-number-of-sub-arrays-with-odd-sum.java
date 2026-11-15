class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = (int) 1e9 + 7;
        int countEven = 1, countOdd = 0;
        int currentSum = 0;
        int result = 0;
        
        for (int num : arr) {
            currentSum = (currentSum + (num % 2)) % 2;
            
            if (currentSum == 1) {
                result = (result + countEven) % MOD;
            } else {
                result = (result + countOdd) % MOD;
            }
            
            if (currentSum == 1) {
                countOdd++;
            } else {
                countEven++;
            }
        }
        
        return result;
    }
}