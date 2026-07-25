public class PalindromePartitioning {
  static Integer[][] dp = new Integer[101][101];

  public static int palindromePartition(String s, int i, int j) {
    if (i >= j)
      return 0;

    if (dp[i][j] != null)
      return dp[i][j];

    if (isPalindrome(s, i, j))
      return 0;

    int minCost = Integer.MAX_VALUE;

    for (int k = i; k < j; k++) {
      int leftCost = (dp[i][k] != null) ? dp[i][k] : palindromePartition(s, i, k);
      int rightCost = (dp[k + 1][j] != null) ? dp[k + 1][j] : palindromePartition(s, k + 1, j);

      int currentCost = leftCost + rightCost + 1;

      if (currentCost < minCost) {
        minCost = currentCost;
      }
    }

    return dp[i][j] = minCost;
  }

  public static void main(String[] args) {
    String s = "ababbbabbababa";

    int i = 0;
    int j = s.length() - 1;

    int minCuts = palindromePartition(s, i, j);

    System.out.println("String: " + s);
    System.out.println("Minimum number of cuts: " + minCuts); // Expected output: 3
  }
  
  public static boolean isPalindrome(String s, int i, int j) {
    while (i < j) {
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      }
      i++;
      j--;
    }
    return true;
  }
}
