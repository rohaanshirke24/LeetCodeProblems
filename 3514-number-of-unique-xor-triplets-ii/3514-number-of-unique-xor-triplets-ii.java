class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int LIMIT = 2048;

        boolean[] pairXor = new boolean[LIMIT];

        for (int a : nums) {
            for (int b : nums) {
                pairXor[a ^ b] = true;
            }
        }

        boolean[] tripletXor = new boolean[LIMIT];

        for (int xor = 0; xor < LIMIT; xor++) {
            if (!pairXor[xor]) continue;

            for (int c : nums) {
                tripletXor[xor ^ c] = true;
            }
        }

        int answer = 0;
        for (boolean exists : tripletXor) {
            if (exists) answer++;
        }

        return answer;
    }
}