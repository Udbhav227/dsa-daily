/*
Given a set of non-negative integers and a target sum, determine if there exists a non-empty
subset of the given set whose elements sum up to the target value.

Input: An array nums of n non-negative integers and a target integer sum.
Output: true if any subset of the array adds up to sum, otherwise false.
*/

public class SubsetSum {

  public static boolean isSubsetSum(int[] nums, int target) {
    int n = nums.length;
    boolean[][] dp = new boolean[n + 1][target + 1];

    // Base Case 1: If sum is 0, we can always achieve it with an empty set
    for (int i = 0; i <= n; i++) {
      dp[i][0] = true;
    }

    // Base Case 2: If no items are left but target > 0, it's impossible
    for (int j = 1; j <= target; j++) {
      dp[0][j] = false;
    }

    // Fill the table
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= target; j++) {
        if (nums[i - 1] <= j) {
          // Two choices: Include the item OR Exclude the item
          dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j];
        } else {
          // Cannot include the item
          dp[i][j] = dp[i - 1][j];
        }
      }
    }

    return dp[n][target];
  }
}
