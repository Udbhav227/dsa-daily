// recursive approach
private static int LCS(String x, String y, int n, int m) {
    if (n == 0 || m == 0)
        return 0;

    if (x.charAt(n - 1) == y.charAt(m - 1)) {
        return 1 + LCS(x, y, n - 1, m - 1);
    } else {
        return Math.max(LCS(x, y, n - 1, m), LCS(x, y, n, m - 1));
    }
}

// memoised version
private static int LCS(String x, String y, int n, int m) {
  if (n == 0 || m == 0) return 0;

  if (memo[n][m] != -1) return memo[n][m];

  if (x.charAt(n - 1) == y.charAt(m - 1)) {
    return memo[n][m] = 1 + LCS(x, y, n - 1, m - 1);
  } else {
    return memo[n][m] = Math.max(
      LCS(x, y, n - 1, m),
      LCS(x, y, n, m - 1));
  }
}

// bottom-up or tabulation 
public class LongestCommonSubsequence {

    public static int LCS(String x, String y) {
        int n = x.length();
        int m = y.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } 
                // Matching character: add 1 to diagonal value
                else if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } 
                // Mismatch: take the maximum of skipping a char from x or y
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n][m];
    }
}
