class Solution {
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int skillCount = req_skills.length;
        int fullMask = (1 << skillCount) - 1;
        Map<String, Integer> skillToBit = new HashMap<>();
        for (int i = 0; i < skillCount; i++) {
            skillToBit.put(req_skills[i], i);
        }
        int n = people.size();
        int[] personMasks = new int[n];

        for (int i = 0; i < n; i++) {
            for (String skill : people.get(i)) {
                personMasks[i] |= 1 << skillToBit.get(skill);
            }
        }
        List<Integer>[] dp = new ArrayList[1 << skillCount];
        dp[0] = new ArrayList<>();
        for (int person = 0; person < n; person++) {
            int personMask = personMasks[person];
            for (int mask = fullMask; mask >= 0; mask--) {
                if (dp[mask] == null) {
                    continue;
                }
                int combinedMask = mask | personMask;
                if (combinedMask == mask) {
                    continue;
                }
                List<Integer> candidate = new ArrayList<>(dp[mask]);
                candidate.add(person);
                if (dp[combinedMask] == null ||
                    candidate.size() < dp[combinedMask].size()) {
                    dp[combinedMask] = candidate;
                }
            }
        }
        List<Integer> answer = dp[fullMask];
        int[] result = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }
}