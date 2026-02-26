class Solution {
    public int numSteps(String s) {
        int steps = 0;
        int carry = 0;

        // Iterate through the binary string from right to left
        for (int i = s.length() - 1; i > 0; i--) {
            // If the current digit is '0', it's even, so divide the number by 2
            if (s.charAt(i) == '0') {
                // If there's a carry from previous addition, add 1 to the number
                if (carry == 1) {
                    steps += 2; // One for the division and one for the carry
                } else {
                    steps++; // Only division
                }
            } else { // If the current digit is '1', it's odd, so add 1 to the number
                if (carry == 1) {
                    steps++; // Only the carry
                } else {
                    steps += 2; // One for the addition and one for the division
                    carry = 1; // Set carry for next iteration
                }
            }
        }
        
        // For the last bit, if it's '1', one more step is needed to make it '0'
        steps += carry;
        
        return steps;
    }
}