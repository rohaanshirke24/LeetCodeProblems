class Solution {
    public String makeLargestSpecial(String s) {
        if (s.length() <= 1) {
            return s;
        }

        List<String> specialSubstrings = new ArrayList<>();
        int count = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            count += s.charAt(i) == '1' ? 1 : -1;
            if (count == 0) { 
                String inner = s.substring(start + 1, i);
                specialSubstrings.add("1" + makeLargestSpecial(inner) + "0");
                start = i + 1;
            }
        }
        specialSubstrings.sort((a, b) -> b.compareTo(a));
        StringBuilder result = new StringBuilder();
        for (String sub : specialSubstrings) {
            result.append(sub);
        }
        
        return result.toString();
    }
}