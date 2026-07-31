class Solution {
  public int minCostClimbingStairs(int[] cost) {
    int n = cost.length;

    int dp[] = new int[n + 1];

    dp[0] = 0;
    dp[1] = 0;

    for (int i = 2; i <= n; i++) {
      int one = cost[i - 1] + dp[i - 1];
      int two = cost[i - 2] + dp[i - 2];

      dp[i] = Math.min(one, two);
    }

    return dp[n];
  }
}
