# Recursive + Memoization Solution

```java
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
```

---

# Bottom-Up (Tabulation) Solution

```java
import java.util.Arrays;

public class MatrixChainMultiplication {

    public static int mcmDP(int[] p) {
        int n = p.length;
        int[][] dp = new int[n][n];

        // Cost is zero when multiplying one matrix
        // (already initialized to 0 by Java)

        // L is the chain length
        for (int L = 2; L < n; L++) {
            for (int i = 1; i < n - L + 1; i++) {
                int j = i + L - 1;
                dp[i][j] = Integer.MAX_VALUE;

                // Try all possible split points between i and j
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + (p[i - 1] * p[k] * p[j]);

                    if (cost < dp[i][j]) {
                        dp[i][j] = cost;
                    }
                }
            }
        }

        // The result for the full chain is stored in the top-right corner
        return dp[1][n - 1];
    }
}
```

---

# Bottom-Up (Tabulation) Explanation

To understand the bottom-up tabulation approach, you have to invert how you think about the problem.

In top-down memoization, you start with the entire chain (e.g., matrices 1 through 4) and break it down. In bottom-up tabulation, you start by solving the smallest possible chains first, caching their results, and using those to build slightly larger chains until you have solved the whole thing.

Here is the exact logic of the three loops that make this happen.

```java
// n is the length of the dimensions array p.
// The actual number of matrices is n - 1.
for (int L = 2; L < n; L++) {
    for (int i = 1; i < n - L + 1; i++) {
        int j = i + L - 1;
        dp[i][j] = Integer.MAX_VALUE;

        for (int k = i; k < j; k++) {
            int cost = dp[i][k] + dp[k + 1][j] + (p[i - 1] * p[k] * p[j]);

            if (cost < dp[i][j]) {
                dp[i][j] = cost;
            }
        }
    }
}
```

---

## 1. The Outer Loop: Chain Length (`L`)

```java
for (int L = 2; L < n; L++)
```

The variable `L` represents the **length of the matrix chain** we are currently solving.

* **Why start at 2?** A chain of length 1 (a single matrix) requires 0 multiplications. In Java, the `dp` array is initialized to `0` by default, so the base cases (`dp[1][1]`, `dp[2][2]`, etc.) are already solved. The smallest problem we actually need to calculate is multiplying **two** matrices together.

* **How it scales:** The loop calculates the optimal cost for every chain of length 2. Only when **all** length-2 chains are calculated does it move to length 3, and so on, up to the full chain of length `n - 1`.

---

## 2. The Middle Loop: Sliding Window Start (`i`)

```java
for (int i = 1; i < n - L + 1; i++) {
    int j = i + L - 1;
```

This loop slides a "window" of size `L` across our matrices.

* **`i` is the starting matrix** of our current subchain.

* **`j` is the ending matrix.** We calculate `j` simply by adding the chain length `L` to our start point `i`, and subtracting `1`.

* **The bound `n - L + 1`:** This stops the window from sliding off the edge of the array. For example, if we have 4 matrices (`n = 5`) and are looking at chains of length `L = 3`, the last valid starting matrix is `i = 2` (which covers matrices 2, 3, and 4).

---

## 3. The Inner Loop: The Partition Point (`k`)

```java
for (int k = i; k < j; k++) {
```

Now that we have isolated a specific chain from matrix `i` to matrix `j`, we need to find the best place to split it.

* `k` is the exact point where we draw our outermost parentheses. It divides the chain into a left half (`i` to `k`) and a right half (`k + 1` to `j`).

* Because we built the table from the bottom up, the minimum costs for both the left half (`dp[i][k]`) and the right half (`dp[k + 1][j]`) **are guaranteed to already be calculated** and waiting in the `dp` array.

* The loop tries every possible split point `k` between `i` and `j - 1`, calculates the total cost of that specific split, and saves the cheapest one to `dp[i][j]`.
