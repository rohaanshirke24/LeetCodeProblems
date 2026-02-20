class Solution {
    public String makeLargestSpecial(String s) {
        // Base case: if s is empty, return it as it is
        if (s.length() <= 1) {
            return s;
        }

        List<String> specialSubstrings = new ArrayList<>();
        int count = 0;
        int start = 0;
        
        // Step 1: Divide the string into special substrings
        for (int i = 0; i < s.length(); i++) {
            count += s.charAt(i) == '1' ? 1 : -1;
            if (count == 0) { // found a balanced substring
                // Recurse on the inner part of the special substring
                String inner = s.substring(start + 1, i);
                specialSubstrings.add("1" + makeLargestSpecial(inner) + "0");
                start = i + 1;
            }
        }

        // Step 2: Sort the substrings in descending order
        specialSubstrings.sort((a, b) -> b.compareTo(a));

        // Step 3: Join them to form the final result
        StringBuilder result = new StringBuilder();
        for (String sub : specialSubstrings) {
            result.append(sub);
        }
        
        return result.toString();
    }
}