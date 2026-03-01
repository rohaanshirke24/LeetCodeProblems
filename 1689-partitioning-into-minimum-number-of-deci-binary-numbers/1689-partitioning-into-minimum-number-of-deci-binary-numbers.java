class Solution {
    public int minPartitions(String n) {
        int maxDigit = 0;

        // Iterate over each character in the string
        for (char c : n.toCharArray()) {
            // Convert character to its numeric value
            int digit = c - '0';

            // Update maxDigit if the current digit is greater
            if (digit > maxDigit) {
                maxDigit = digit;
            }

            // Early exit: If we find a digit '9', that's the maximum possible
            if (maxDigit == 9) {
                break;
            }
        }

        return maxDigit;
    }
}