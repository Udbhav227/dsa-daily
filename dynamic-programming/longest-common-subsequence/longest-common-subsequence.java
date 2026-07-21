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
public int longestCommonSubsequence(String text1, String text2) {
  int m = text1.length();
  int n = text2.length();

  int[][] dp = new int[m + 1][n + 1];

  for (int i = 1; i <= m; i++) {
    for (int j = 1; j <= n; j++) {
      if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
        dp[i][j] = 1 + dp[i - 1][j - 1];
      } else {
        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
      }
    }
  }

  return dp[m][n];
}
