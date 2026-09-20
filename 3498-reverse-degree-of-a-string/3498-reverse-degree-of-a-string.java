class Solution {
    public int reverseDegree(String s) {
        int totalDegree = 0;
        for (int i = 0; i < s.length(); i++) {
            int reversedAlphabetPos = 'z' - s.charAt(i) + 1;
            int stringPos = i + 1;
            totalDegree += reversedAlphabetPos * stringPos;
        }
        return totalDegree;
    }
}