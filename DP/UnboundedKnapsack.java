// val[] = {15, 14, 10, 45, 30}
// wt[] = {2, 5, 1, 3, 4}
// W (total allowed weight) = 7
// ans = maxprofit

// in 0 1 knapsack we used to include one item at only once but in unbounded kanapsack one item can be used many times
// almost similar to the 0 1 knapsack

package DP;

public class UnboundedKnapsack {

  public static int UBknapsack(int val[], int wt[], int W) {
    int n = val.length;
    int dp[][] = new int[n + 1][W + 1]; // creating 2d array
    for (int i = 0; i < n + 1; i++) { //initailization 0th row
      dp[i][0] = 0;
    }
    for (int j = 0; j < W + 1; j++) { //initailization 0th col
      dp[0][j] = 0;
    }
    for (int i = 1; i < n + 1; i++) {
      for (int j = 1; j < W + 1; j++) {
        int v = val[i - 1];
        int w = wt[i - 1];
        if (w <= j) {
          int incProfit = v + dp[i][j - w]; // instead of dp[i-1][j-w] we used dp[i][j-w], in 0 1 knapsack we used to add element only once so once we add it we dont add it again so we used to subtract 1 that is skipping the current element, in unbounded we dont skip use current element again so we dont subtract 1
          int excProfit = dp[i - 1][j];
          dp[i][j] = Math.max(incProfit, excProfit);
        } else {
          int excProfit = dp[i - 1][j];
          dp[i][j] = excProfit;
        }
      }
    }
    return dp[n][W];
  }

  public static void main(String[] args) {
    int W = 7;
    int val[] = { 15, 14, 10, 45, 30 };
    int wt[] = { 2, 5, 1, 3, 4 };
    System.out.println(UBknapsack(val, wt, W));
  }
}
