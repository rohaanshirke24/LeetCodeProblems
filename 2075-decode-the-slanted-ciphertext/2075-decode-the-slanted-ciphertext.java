class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        if (rows == 0 || encodedText.length() == 0) {
            return "";
        }

        int cols = encodedText.length() / rows;
        StringBuilder ans = new StringBuilder();

        for (int startCol = 0; startCol < cols; startCol++) {
            int r = 0, c = startCol;
            while (r < rows && c < cols) {
                ans.append(encodedText.charAt(r * cols + c));
                r++;
                c++;
            }
        }

        while (ans.length() > 0 && ans.charAt(ans.length() - 1) == ' ') {
            ans.deleteCharAt(ans.length() - 1);
        }

        return ans.toString();
    }
}