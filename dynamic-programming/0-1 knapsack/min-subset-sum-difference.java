/**
 * Finds the minimum absolute difference between the sums of two subsets.
 */

public class SubsetSumDifference {

  public static int findMinDifference(int[] nums) {
    int n = nums.length;
    int sum = 0;
    for (int num : nums) {
      sum += num;
    }

    int target = sum / 2;

    boolean[][] dp = new boolean[n + 1][target + 1];

    isSubsetSum(nums, target, dp);

    for (int i = target; i >= 0; i--) {
      if (dp[n][i]) {
        return sum - (2 * i);
      }
    }

    return sum;
  }

  public static void isSubsetSum(int[] nums, int target, boolean[][] dp) {
    int n = nums.length;

    for (int i = 0; i <= n; i++) {
      dp[i][0] = true;
    }

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= target; j++) {
        if (nums[i - 1] <= j) {
          dp[i][j] = dp[i - 1][j] || dp[i-1][j - nums[i - 1]];
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }
  }
}
