class Solution {
    public long countPalindromePaths(List<Integer> parent, String s) {
        int n = parent.size();
        int[] head = new int[n];
        Arrays.fill(head, -1);
        int[] next = new int[n];
        
        for (int i = 1; i < n; i++) {
            int p = parent.get(i);
            next[i] = head[p];
            head[p] = i;
        }
        int[] mask = new int[n];
        int[] queue = new int[n];
        int headPtr = 0, tailPtr = 0;
        
        queue[tailPtr++] = 0;
        
        while (headPtr < tailPtr) {
            int u = queue[headPtr++];
            for (int v = head[u]; v != -1; v = next[v]) {
                int charBit = 1 << (s.charAt(v) - 'a');
                mask[v] = mask[u] ^ charBit;
                queue[tailPtr++] = v;
            }
        }
        Map<Integer, Integer> maskCount = new HashMap<>();
        long totalPairs = 0;
        
        for (int i = 0; i < n; i++) {
            int m = mask[i];
            totalPairs += maskCount.getOrDefault(m, 0);
            for (int k = 0; k < 26; k++) {
                totalPairs += maskCount.getOrDefault(m ^ (1 << k), 0);
            }
            maskCount.put(m, maskCount.getOrDefault(m, 0) + 1);
        }
        
        return totalPairs;
    }
}