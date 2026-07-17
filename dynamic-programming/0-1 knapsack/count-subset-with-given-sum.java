/*
Given an array nums of n non-negative integers and a target integer sum, determine the
total number of distinct subsets of the array that add up to exactly the given sum.

Input: An array nums and an integer target.
Output: An integer representing the count of all possible subsets that sum to target.
*/

public class CountSubsetSum {
  public static int countSubsets(int[] nums, int target) {
    int n = nums.length;
    int[][] dp = new int[n + 1][target + 1];

    for (int i = 0; i <= n; i++) {
      dp[i][0] = 1;
    }

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= target; j++) {
        if (nums[i - 1] <= j) {
          dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }
    return dp[n][target];
  }

  public static void main(String[] args) {
    int[] nums = {1, 2, 3, 3};
    int target = 6;

    int result = countSubsets(nums, target);
    System.out.println("Total number of subsets that sum to " + target + " is: " + result);
    // Expected output: 3
  }
}
