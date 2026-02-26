class Solution {
    public int numSteps(String s) {
        int steps = 0;
        int carry = 0;
        for (int i = s.length() - 1; i > 0; i--) {
            if (s.charAt(i) == '0') {
                if (carry == 1) {
                    steps += 2; 
                } else {
                    steps++; 
                }
            } else { 
                if (carry == 1) {
                    steps++; 
                } else {
                    steps += 2; 
                    carry = 1; 
                }
            }
        }
        steps += carry;
        
        return steps;
    }
}