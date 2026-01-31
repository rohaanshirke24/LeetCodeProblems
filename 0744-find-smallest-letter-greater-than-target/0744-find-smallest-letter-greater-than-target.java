class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0;
        int right = letters.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (letters[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // If left is out of bounds, return the first character
        if (left >= letters.length) {
            return letters[0];
        }
        // Otherwise, return the character at the left index
        return letters[left];
    }
}