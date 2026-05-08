class Solution {
    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        Map<Integer, Set<Integer>> userMinutes = new HashMap<>();

        for (int[] log : logs) {
            int id = log[0];
            int time = log[1];
            userMinutes.computeIfAbsent(id, x -> new HashSet<>()).add(time);
        }

        int[] answer = new int[k];

        for (Set<Integer> minutes : userMinutes.values()) {
            int uam = minutes.size();
            answer[uam - 1]++;
        }

        return answer;
    }
}