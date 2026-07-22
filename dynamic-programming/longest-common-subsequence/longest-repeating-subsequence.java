class Solution {
    public int longestRepeatingSubsequence(String s) {
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];

        // Compute LCS(s, s) while disallowing matching
        // the same character occurrence with itself.
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {

                // Characters must be equal and come from different indices.
                // Otherwise, we'd simply match every character with itself,
                // making the answer equal to the original string.
                if (s.charAt(i - 1) == s.charAt(j - 1) && i != j) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n][n];
    }
}
