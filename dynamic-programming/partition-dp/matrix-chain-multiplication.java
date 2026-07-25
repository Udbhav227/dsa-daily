// Recusive + memo solution
import java.util.Arrays;

public class MatrixChainMultiplication {
  static Integer[][] dp = new Integer[101][101];

  public static int MCM(int[] p, int i, int j) {
    if (i >= j)
      return 0;

    if (dp[i][j] != null)
      return dp[i][j];

    int minCost = Integer.MAX_VALUE;

    for (int k = i; k < j; k++) {
      // Check cache before calculating the left subproblem
      int leftCost = (dp[i][k] != null) ? dp[i][k] : MCM(p, i, k);

      // Check cache before calculating the right subproblem
      int rightCost = (dp[k + 1][j] != null) ? dp[k + 1][j] : MCM(p, k + 1, j);

      // Calculate the temporary current cost
      int currentCost = leftCost + rightCost + (p[i - 1] * p[k] * p[j]);

      if (currentCost < minCost) {
        minCost = currentCost;
      }
    }

    return dp[i][j] = minCost;
  }

  public static void main(String[] args) {
    int[] dimensions = {1, 2, 3, 4, 3}; // ans 30

    int i = 1;
    int j = dimensions.length - 1;
    int minCost = MCM(dimensions, i, j);

    System.out.println("Matrix Dimensions: " + Arrays.toString(dimensions));
    System.out.println("Minimum number of multiplications: " + minCost);
  }
}
