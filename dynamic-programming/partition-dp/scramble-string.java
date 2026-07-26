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
