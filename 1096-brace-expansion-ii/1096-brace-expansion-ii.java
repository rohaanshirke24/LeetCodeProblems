class Solution {
    int i = 0;
    public List<String> braceExpansionII(String expression) {
        Set<String> result = expr(expression);
        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);
        return ans;
    }
    private Set<String> expr(String s) {
        Set<String> result = new TreeSet<>();
        result.addAll(term(s));
        while (i < s.length() && s.charAt(i) == ',') {
            i++; 
            result.addAll(term(s));
        }
        return result;
    }
    private Set<String> term(String s) {
        Set<String> result = new TreeSet<>();
        result.add("");
        while (i < s.length() && s.charAt(i) != ',' && s.charAt(i) != '}') {
            Set<String> next = factor(s);
            Set<String> combined = new TreeSet<>();
            for (String a : result)
                for (String b : next)
                    combined.add(a + b);
            result = combined;
        }
        return result;
    }

    private Set<String> factor(String s) {
        if (s.charAt(i) == '{') {
            i++; 
            Set<String> result = expr(s);
            i++; 
            return result;
        } else {
            Set<String> result = new TreeSet<>();
            result.add(String.valueOf(s.charAt(i++)));
            return result;
        }
    }
}