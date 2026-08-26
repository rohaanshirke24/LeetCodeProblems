class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        List<Integer> ones = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                ones.add(i);
            }
        }
        if (ones.size() < k) {
            return "";
        }
        
        String result = "";
        for (int i = 0; i + k - 1 < ones.size(); i++) {
            int start = ones.get(i);
            int end = ones.get(i + k - 1);
            String candidate = s.substring(start, end + 1);
            
            if (result.isEmpty() || candidate.length() < result.length()) {
                result = candidate;
            } else if (candidate.length() == result.length() && candidate.compareTo(result) < 0) {
                result = candidate;
            }
        }
        
        return result;
    }
}