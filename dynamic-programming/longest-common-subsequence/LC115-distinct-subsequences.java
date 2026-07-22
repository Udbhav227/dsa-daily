// recursive approach
class Solution {
  public int numDistinct(String s, String t) {
    return solve(s.length(), t.length(), s, t);
  }

  public int solve(int m, int n, String s, String t) {
    if (n == 0)
      return 1;

    if (m == 0) 
      return 0;

    if (s.charAt(m-1) == t.charAt(n-1)) {
      return solve(m-1, n-1, s, t) + solve(m-1, n, s, t);
    } else {
      return solve(m-1, n, s, t);
    }
  }
}

// memoize 
class Solution {
  static Integer[][] memo;
  public int numDistinct(String s, String t) {
    memo = new Integer[s.length() + 1][t.length() + 1];
    return solve(s.length(), t.length(), s, t);
  }

  public int solve(int m, int n, String s, String t) {
    if (n == 0)
      return 1;

    if (m == 0) 
      return 0;

    if (memo[m][n] != null)
      return memo[m][n];

    if (s.charAt(m-1) == t.charAt(n-1)) {
      memo[m][n] = solve(m-1, n-1, s, t) + solve(m-1, n, s, t);
    } else {
      memo[m][n] = solve(m-1, n, s, t);
    }

    return memo[m][n];
  }
}

// bottom up
class Solution {

  public int numDistinct(String s, String t) {

    int m = s.length();
    int n = t.length();

    int[][] dp = new int[m + 1][n + 1];

    // Empty target can always be formed.
    for (int i = 0; i <= m; i++) {
      dp[i][0] = 1;
    }

    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {

        if (s.charAt(i - 1) == t.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }

    return dp[m][n];
  }
}
