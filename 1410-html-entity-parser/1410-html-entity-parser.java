class Solution {
    public String entityParser(String text) {
        Map<String, String> map = new HashMap<>();
        map.put("&quot;", "\"");
        map.put("&apos;", "'");
        map.put("&amp;", "&");
        map.put("&gt;", ">");
        map.put("&lt;", "<");
        map.put("&frasl;", "/");
        
        StringBuilder ans = new StringBuilder();
        int n = text.length();
        
        for (int i = 0; i < n; i++) {
            if (text.charAt(i) == '&') {
                int j = i;
                while (j < n && text.charAt(j) != ';' && j - i <= 7) {
                    j++;
                }
                
                if (j < n && text.charAt(j) == ';') {
                    String candidate = text.substring(i, j + 1);
                    if (map.containsKey(candidate)) {
                        ans.append(map.get(candidate));
                        i = j;
                        continue;
                    }
                }
            }
            ans.append(text.charAt(i));
        }
        
        return ans.toString();
    }
}