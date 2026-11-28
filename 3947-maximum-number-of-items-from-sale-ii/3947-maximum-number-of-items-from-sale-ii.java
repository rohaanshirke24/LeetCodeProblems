class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freq = new int[n + 1];
        long pMin = Long.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            if (item[1] < pMin) {
                pMin = item[1];
            }
        }
        int[] multCount = new int[n + 1];
        for (int f = 1; f <= n; f++) {
            if (freq[f] == 0) {
                continue;
            }
            for (int m = f; m <= n; m += f) {
                multCount[f] += freq[m];
            }
        }

        Arrays.sort(items, Comparator.comparingInt(a -> a[1]));

        long b = budget;
        long curCost = 0;
        long k = 0; 
        long ans = b / pMin;

        for (int[] item : items) {
            long price = item[1];
            if (price >= 2 * pMin) {
                break;
            }

            long m = multCount[item[0]] - 1;
            if (m <= 0) {
                continue;
            }

            long canTake = Math.min(m, (b - curCost) / price);
            if (canTake > 0) {
                curCost += canTake * price;
                k += canTake;
                ans = Math.max(ans, 2 * k + (b - curCost) / pMin);
            }

            if (canTake < m) {
                break;
            }
        }

        return (int) ans;
    }
}