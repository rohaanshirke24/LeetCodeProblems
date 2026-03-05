class Solution {
    public int minOperations(String s) {
        // Initialize counters for two possible starting characters '0' and '1'
        int operationsStartWithZero = 0;
        int operationsStartWithOne = 0;

        // Iterate through the string
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            // Check if the current position should have '0'
            if (i % 2 == 0) {
                if (currentChar == '0') {
                    operationsStartWithOne++;
                } else {
                    ;operationsStartWithZero++;
                }
            } else { // Check if the current position should have '1'
                if (currentChar == '1') {
                    operationsStartWithOne++;
                } else {
                    operationsStartWithZero++;
                }
            }
        }

        // Return the minimum operations needed by taking the minimum of the two cases
        return Math.min(operationsStartWithZero, operationsStartWithOne);
    }
}