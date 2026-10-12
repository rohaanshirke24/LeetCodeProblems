class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        float[] timeAtPos = new float[target];
        for (int i = 0; i < position.length; i++) {
            timeAtPos[position[i]] = (float) (target - position[i]) / speed[i];
        }
        
        int fleets = 0;
        float currentFleetTime = 0;
        for (int p = target - 1; p >= 0; p--) {
            if (timeAtPos[p] > currentFleetTime) {
                fleets++;
                currentFleetTime = timeAtPos[p];
            }
        }
        return fleets;
    }
}