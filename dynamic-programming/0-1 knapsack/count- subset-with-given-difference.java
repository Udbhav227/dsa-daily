/**
 * Objective:
 * Given an array of positive integers 'arr' and a target difference 'diff',
 * count the total number of ways to partition the array into two subsets, 
 * S1 and S2, such that the absolute difference between their sums is exactly equal to 'diff'.
 */

public class NumberOfSubsetsWithDifference {
  public static int countSubsetsWithDifference(int[] nums, int diff) {
    int sum = 0;
    for (int n : nums) {
      sum += n;
    }

    if (diff > sum || (sum + diff) % 2 != 0 || (sum + diff) < 0) {
      return 0;
    }

    int s1 = (sum + diff) / 2;
    return countSubsets(nums, s1);
  }

  public static int countSubsets1D(int[] nums, int target) {
    int[] dp = new int[target + 1];
    dp[0] = 1;

    for (int num : nums) {
        for (int j = target; j >= num; j--) {
            dp[j] = dp[j] + dp[j - num];
        }
    }
    return dp[target];
  }
}
