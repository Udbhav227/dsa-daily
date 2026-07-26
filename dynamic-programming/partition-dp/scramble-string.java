// Recursive solution
class Solution {

    public boolean isScramble(String s1, String s2) {
        return solve(s1, s2);
    }

    private boolean solve(String a, String b) {

        // Base cases
        if (a.equals(b)) {
            return true;
        }

        if (a.length() <= 1) {
            return false;
        }

        int n = a.length();

        for (int i = 1; i <= n - 1; i++) {

            // Case 1: Swap
            if (solve(a.substring(0, i), b.substring(n - i)) &&
                solve(a.substring(i), b.substring(0, n - i))) {
                return true;
            }

            // Case 2: No Swap
            if (solve(a.substring(0, i), b.substring(0, i)) &&
                solve(a.substring(i), b.substring(i))) {
                return true;
            }
        }

        return false;
    }
}

// Memo + optimistaion
import java.util.HashMap;

class Solution {

  HashMap<String, Boolean> memo = new HashMap<>();

  public boolean isScramble(String s1, String s2) {
    return solve(s1, s2);
  }

  private boolean solve(String a, String b) {

    if (a.equals(b))
      return true;

    if (a.length() != b.length())
      return false;

    String key = a + " " + b;

    if (memo.containsKey(key))
      return memo.get(key);

    int n = a.length();

    // Character frequency check further optimisation It quickly checks whether two strings contain the same characters with the same frequencies before trying expensive recursive scrambling.
    // int[] freq = new int[26];
    // for (int i = 0; i < n; i++) {
    //   freq[a.charAt(i) - 'a']++;
    //   freq[b.charAt(i) - 'a']--;
    // }

    // for (int x : freq) {
    //   if (x != 0) {
    //     memo.put(key, false);
    //     return false;
    //   }
    // }

    for (int i = 1; i < n; i++) {

      // Swap case
      if (solve(a.substring(0, i), b.substring(n - i)) &&
          solve(a.substring(i), b.substring(0, n - i))) {
        memo.put(key, true);
        return true;
      }

      // No swap case
      if (solve(a.substring(0, i), b.substring(0, i)) &&
          solve(a.substring(i), b.substring(i))) {
        memo.put(key, true);
        return true;
      }
    }

    memo.put(key, false);
    return false;
  }
}
