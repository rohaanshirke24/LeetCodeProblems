class Solution {
    public long minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        Arrays.sort(horizontalCut);
        Arrays.sort(verticalCut);
        
        int i = horizontalCut.length - 1;
        int j = verticalCut.length - 1;
        long hPieces = 1;
        long vPieces = 1;
        long totalCost = 0;
        while (i >= 0 && j >= 0) {
            if (horizontalCut[i] >= verticalCut[j]) {
                totalCost += (long) horizontalCut[i] * vPieces;
                hPieces++;
                i--;
            } else {
                totalCost += (long) verticalCut[j] * hPieces;
                vPieces++;
                j--;
            }
        }
        while (i >= 0) {
            totalCost += (long) horizontalCut[i] * vPieces;
            i--;
        }
        while (j >= 0) {
            totalCost += (long) verticalCut[j] * hPieces;
            j--;
        }
        
        return totalCost;
    }
}