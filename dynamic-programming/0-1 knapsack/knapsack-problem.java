/*
You are a traveler packing a bag for a trip. 
You have N items to choose from. Each item has a specific weight and a specific value (or utility). 
Your bag can only hold a maximum weight capacity of W.

Find the maximum total value you can pack in your bag without exceeding the weight limit. 
Because this is the 0/1 Knapsack problem, 
you cannot split items—you must either take an item entirely or leave it behind.
*/

// Foundation step
public class KnapsackProblem {

  public static int knapSack(int W, int[] wt, int[] val, int n) {
    Integer[][] memo = new Integer[n + 1][W + 1];
    return knapSackRecursive(W, wt, val, n, memo);
  }

  private static int knapSackRecursive(int W, int[] wt, int[] val, int n, Integer[][] memo) {
    if (n == 0 || W == 0) return 0;

    if (memo[n][W] != null) return memo[n][W];

    int result;
    if (wt[n - 1] <= W) {
      result = Math.max(
        val[n - 1] + knapSackRecursive(W - wt[n - 1], wt, val, n - 1, memo),
        knapSackRecursive(W, wt, val, n - 1, memo)
      );
    } else {
      result = knapSackRecursive(W, wt, val, n - 1, memo);
    }

    memo[n][W] = result;
    return result;
  }
}

// Final code after tabulation
public class KnapsackProblem {

  public static int knapSack(int W, int[] wt, int[] val, int n) {
    int[][] dp = new int[n + 1][W + 1];

    for (int i = 0; i <= n; i++) {
      for (int w = 0; w <= W; w++) {
        if (i == 0 || w == 0) {
          dp[i][w] = 0;
        } else if (wt[i - 1] <= w) {
          dp[i][w] = Math.max(
            val[i - 1] + dp[i - 1][w - wt[i - 1]],
            dp[i - 1][w]
          );
        } else {
          dp[i][w] = dp[i - 1][w];
        }
      }
    }
    return dp[n][W];
  }
}
