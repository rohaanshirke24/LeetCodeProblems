class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {
        int landThenWater = calc(landStartTime, landDuration, waterStartTime, waterDuration);
        int waterThenLand = calc(waterStartTime, waterDuration, landStartTime, landDuration);
        return Math.min(landThenWater, waterThenLand);
    }

    private int calc(int[] firstStart, int[] firstDuration,
                     int[] secondStart, int[] secondDuration) {
        int earliestFirstFinish = Integer.MAX_VALUE;

        for (int i = 0; i < firstStart.length; i++) {
            earliestFirstFinish = Math.min(earliestFirstFinish, firstStart[i] + firstDuration[i]);
        }

        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < secondStart.length; i++) {
            int finishTime = Math.max(earliestFirstFinish, secondStart[i]) + secondDuration[i];
            answer = Math.min(answer, finishTime);
        }

        return answer;
    }
}