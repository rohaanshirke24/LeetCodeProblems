class Solution {
    public long beautifulSubarrays(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        freq.put(0, 1); 
        long ans = 0;
        int prefix = 0;
        for (int x : nums) {
            prefix ^= x;
            int same = freq.getOrDefault(prefix, 0);
            ans += same; 
            freq.put(prefix, same + 1);
        }
        return ans;
    }
}
