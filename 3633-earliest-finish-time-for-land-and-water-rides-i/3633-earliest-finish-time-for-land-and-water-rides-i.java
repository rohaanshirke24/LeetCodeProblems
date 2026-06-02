class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < landStartTime.length; i++) {
            for (int j = 0; j < waterStartTime.length; j++) {
                int landFinish = landStartTime[i] + landDuration[i];
                int waterFinishAfterLand = Math.max(waterStartTime[j], landFinish) + waterDuration[j];
                int waterFinish = waterStartTime[j] + waterDuration[j];
                int landFinishAfterWater = Math.max(landStartTime[i], waterFinish) + landDuration[i];
                ans = Math.min(ans, Math.min(waterFinishAfterLand, landFinishAfterWater));
            }
        }

        return ans;
    }
}