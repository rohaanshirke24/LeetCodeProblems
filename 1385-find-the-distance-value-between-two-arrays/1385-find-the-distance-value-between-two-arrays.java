class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int count = 0;

        for (int x : arr1) {
            if (isDistanceValid(arr2, x - d, x + d)) {
                count++;
            }
        }

        return count;
    }
    private boolean isDistanceValid(int[] arr, int from, int to) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] >= from && arr[mid] <= to) {
                return false; 
            } else if (arr[mid] < from) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return true;
    }
}