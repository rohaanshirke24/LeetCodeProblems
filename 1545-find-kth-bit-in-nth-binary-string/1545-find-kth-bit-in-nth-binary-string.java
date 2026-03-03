class Solution {
    public char findKthBit(int n, int k) {
        return findKthBitRecursive(n, k);
    }

    private char findKthBitRecursive(int n, int k) {
        // Base case: S1 is "0"
        if (n == 1) {
            return '0';
        }

        int length = (1 << n) - 1; // Length of Sn is 2^n - 1
        int mid = (length / 2) + 1; // Mid point of the string

        if (k == mid) {
            return '1'; // The middle bit is always '1'
        } else if (k < mid) {
            // Recur for the first part, which is the same as Sn-1
            return findKthBitRecursive(n - 1, k);
        } else {
            // Recur for the second part, which is reverse(invert(Sn-1))
            // The kth bit in the second part corresponds to the (length - k + 1)th bit in
            // Sn-1
            char result = findKthBitRecursive(n - 1, length - k + 1);
            // Invert the result
            return result == '0' ? '1' : '0';
        }
    }
}