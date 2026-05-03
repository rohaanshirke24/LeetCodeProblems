class Solution {
    public boolean rotateString(String s, String goal) {
        // Check if the lengths of both strings are equal
        if (s.length() != goal.length())
            return false;
        
        // Concatenate the original string with itself
        String concatenated = s + s;
        
        // Check if the concatenated string contains the goal string
        return concatenated.contains(goal);
    }
}