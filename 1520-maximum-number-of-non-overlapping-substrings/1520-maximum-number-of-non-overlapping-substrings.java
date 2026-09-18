class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) {
                first[c] = i;
            }
            last[c] = i;
        }
        List<int[]> intervals = new ArrayList<>();
        
        for (int c = 0; c < 26; c++) {
            if (first[c] == -1) continue;
            
            int l = first[c];
            int r = last[c];
            boolean isValid = true;
            for (int i = l; i <= r; i++) {
                int ch = s.charAt(i) - 'a';
                if (first[ch] < l) {
                    isValid = false; 
                    break;
                }
                r = Math.max(r, last[ch]);
            }
            
            if (isValid) {
                intervals.add(new int[]{l, r});
            }
        }
        intervals.sort((a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(b[0], a[0]);
        });
        List<String> result = new ArrayList<>();
        int prevEnd = -1;
        
        for (int[] interval : intervals) {
            if (interval[0] > prevEnd) {
                result.add(s.substring(interval[0], interval[1] + 1));
                prevEnd = interval[1];
            }
        }
        
        return result;
    }
}