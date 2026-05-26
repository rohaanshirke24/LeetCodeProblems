class Solution {
    public boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) return false;

        if (s.equals(goal)) {
            Set<Character> seen = new HashSet<>();
            for (char c : s.toCharArray()) {
                if (seen.contains(c)) return true; 
                seen.add(c);
            }
            return false; 
        }
        List<Integer> diff = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                diff.add(i);
                if (diff.size() > 2) return false; 
            }
        }
        return diff.size() == 2
            && s.charAt(diff.get(0)) == goal.charAt(diff.get(1))
            && s.charAt(diff.get(1)) == goal.charAt(diff.get(0));
    }
}