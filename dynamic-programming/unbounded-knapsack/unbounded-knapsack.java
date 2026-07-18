public static int unboundedKnapsack(int[] wt, int[] val, int W) {
    int[] dp = new int[W + 1];
    
    for (int i = 0; i < wt.length; i++) {
        // Changing to a forward loop allows reusing the same item 'i'
        for (int j = wt[i]; j <= W; j++) {
            dp[j] = Math.max(dp[j], val[i] + dp[j - wt[i]]);
        }
    }
    return dp[W];
}
