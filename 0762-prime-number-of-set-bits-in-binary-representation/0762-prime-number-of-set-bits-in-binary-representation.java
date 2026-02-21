class Solution {
    public int countPrimeSetBits(int left, int right) {
        int primeMask = (1 << 2)  | (1 << 3)  | (1 << 5)  | (1 << 7) |
                        (1 << 11) | (1 << 13) | (1 << 17) | (1 << 19);
        int count = 0;
        for (int i = left; i <= right; i++) {
            int bits = Integer.bitCount(i);  
            if (((primeMask >> bits) & 1) == 1) {
                count++;
            }
        }
        return count;
    }
}