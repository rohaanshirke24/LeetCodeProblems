class Solution {
    public int bitwiseComplement(int n) {
        if (n == 0) return 1;
        int mask = Integer.highestOneBit(n); 
        mask = (mask << 1) - 1;       

        return n ^ mask;
    }
}