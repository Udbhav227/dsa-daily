// Same as fibonacci
class Solution {
  public int climbStairs(int n) {
    if (n <= 2)
      return n;

    int c1 = 1;
    int c2 = 2;

    for (int i = 3; i <= n; i++) {
      int temp = c2;
      c2 = c1 + c2;
      c1 = temp;
    }

    return c2;
  }
}
