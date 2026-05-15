class Solution {
    public int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
        List<Integer> positions = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == x) {
                positions.add(i);
            }
        }

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int occ = queries[i];
            if (occ <= positions.size()) {
                answer[i] = positions.get(occ - 1);
            } else {
                answer[i] = -1;
            }
        }

        return answer;
    }
}