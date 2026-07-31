class Solution {
  public int fib(int n) {
    Integer[] memo = new Integer[n + 1];
    return fibHelper(n, memo);
  }

  private int fibHelper(int n, Integer[] memo) {
    if (n <= 1) {
      return n;
    }

    if (memo[n] != null) {
      return memo[n];
    }

    memo[n] = fibHelper(n - 1, memo) + fibHelper(n - 2, memo);
    return memo[n];
  }
}

// better option is to just use iterative
class Solution {
  public int fib(int n) {
    if (n <= 1) {
      return n;
    }
    
    int prev2 = 0; // represents fib(n-2)
    int prev1 = 1; // represents fib(n-1)
    
    for (int i = 2; i <= n; i++) {
      int current = prev1 + prev2;
      prev2 = prev1;
      prev1 = current;
    }
    
    return prev1;
  }
}
