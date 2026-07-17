/*
Given a non-empty array nums containing only positive integers, determine if the array can be
partitioned into two subsets such that the sum of the elements in both subsets is equal.

Input: An array nums of n positive integers.
Output: true if the array can be partitioned into two subsets with equal sums, otherwise false.
*/

public class EqualSumPartition {
  public static boolean isSubsetSum(int[] nums, int target) {
    int n = nums.length;
    boolean[][] dp = new boolean[n + 1][target + 1];

    for (int i = 0; i <= n; i++) {
      dp[i][0] = true;
    }

    for (int j = 1; j <= target; j++) {
      dp[0][j] = false;
    }

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= target; j++) {
        if (nums[i - 1] <= j) {
          dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j];
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }

    return dp[n][target];
  }

  public static boolean canPartition(int[] nums) {
    int sum = 0;
    for (int n : nums) {
      sum += n;
    }

    if (sum % 2 != 0) return false;

    return isSubsetSum(nums, sum / 2);
  }

  public static void main(String[] args) {
    int[] nums = {1, 5, 11, 5};

    if (canPartition(nums)) {
      System.out.println("Can be partitioned into equal sum subsets!");
    } else {
      System.out.println("Cannot be partitioned.");
    }
  }
}
