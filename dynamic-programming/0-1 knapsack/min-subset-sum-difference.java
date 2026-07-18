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


// Space Optimisation: using 1D arrya
public static int findMinDifference(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        // We only need to find a subset sum closest to exactly half of the total sum
        int target = totalSum / 2;

        // dp[j] will store whether a subset sum of 'j' is possible
        // Space Complexity: O(target) which is effectively O(Sum)
        boolean[] dp = new boolean[target + 1];
        
        // Base Case: A sum of 0 is always possible using an empty subset
        dp[0] = true; 

        for (int num : nums) {
            // CRITICAL: Iterate backwards from target down to the current number's value.
            // This prevents using the same element multiple times in the same round.
            for (int j = target; j >= num; j--) {
                // If a subset sum of (j - num) was possible before, 
                // adding 'num' makes a subset sum of 'j' possible now.
                if (dp[j - num]) {
                    dp[j] = true;
                }
            }
        }

        // Find the largest subset sum that is achievable and <= totalSum / 2
        int closestSum = 0;
        for (int j = target; j >= 0; j--) {
            if (dp[j]) {
                closestSum = j;
                break; // Break at the very first 'true' since we are moving downwards from the max target
            }
        }

        // Subset 1 sum = closestSum
        // Subset 2 sum = totalSum - closestSum
        // Difference = (totalSum - closestSum) - closestSum = totalSum - 2 * closestSum
        return totalSum - (2 * closestSum);
    }
