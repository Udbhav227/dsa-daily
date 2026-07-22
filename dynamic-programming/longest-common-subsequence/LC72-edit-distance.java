class Solution {
  public int minDistance(String word1, String word2) {
    final int m = word1.length();
    final int n = word2.length();

    int[][] dp = new int[m + 1][n + 1];

    // INITIALIZATION 1: word2 is empty. 
    // To convert any string to an empty string, you must delete every character.
    for (int i = 1; i <= m; ++i)
      dp[i][0] = i;

    // INITIALIZATION 2: word1 is empty. 
    // To build word2 from an empty string, you must insert every character.
    for (int j = 1; j <= n; ++j)
      dp[0][j] = j;

    for (int i = 1; i <= m; ++i) {
      for (int j = 1; j <= n; ++j) {
        
        // Characters match => no new operation needed
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1]; 
        } 
        // Characters differ => try all 3 operations and pick the best
        else {
          dp[i][j] = Math.min(
              dp[i - 1][j - 1],             // 1. Replace character
              Math.min(
                  dp[i - 1][j],             // 2. Delete character from word1
                  dp[i][j - 1]              // 3. Insert character into word1
              )
          ) + 1;                            // +1 accounts for the operation we just did
        }
      }
    }

    return dp[m][n];
  }
}
