// Calculates the minimum number of attempts needed to find the threshold floor.

public class EggDropping {

  static int[][] memo;

  public static int solve(int e, int f) {
    if (e == 1 || f == 1 || f == 0)
      return f;

    if (memo[e][f] != -1)
      return memo[e][f];

    int min = Integer.MAX_VALUE;

    for (int k = 1; k <= f; k++) {
      int temp = 1 + Math.max(solve(e - 1, k - 1), solve(e, f - k));
      min = Math.min(temp, min);
    }

    return memo[e][f] = min;
  }
}
