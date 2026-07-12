class Solution {
    public String decodeMessage(String key, String message) {
        char[] map = new char[26];
        char decodedChar = 'a';
        for (char ch : key.toCharArray()) {
            if (ch != ' ' && map[ch - 'a'] == '\0') {
                map[ch - 'a'] = decodedChar++;
            }
        }

        StringBuilder result = new StringBuilder();
        for (char ch : message.toCharArray()) {
            if (ch == ' ') {
                result.append(' ');
            } else {
                result.append(map[ch - 'a']);
            }
        }

        return result.toString();
    }
}