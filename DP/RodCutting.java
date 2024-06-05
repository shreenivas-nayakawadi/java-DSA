// given a rod length n inches and an array of prices that includes prices of size smaller than n. Determine the maximum value obtained by cutting up the rod and selling into the pieces
// length[] = {1, 2, 3, 4, 5, 6, 7, 8}
// price[] = {1, 5, 8, 9, 10, 17, 17, 20}
// rodLength = 8
// this question is similar to the target sum to refer that only
package DP;

public class RodCutting {

  public static int maxProfit(int length[], int price[], int rodlength) {
    int n = length.length;
    int dp[][] = new int[n + 1][rodlength + 1];
    // initialization
    // j => numbers of item length , i => maxrodlength
    for (int i = 0; i < n + 1; i++) {
      dp[i][0] = 0;
    }
    for (int j = 0; j < rodlength + 1; j++) {
      dp[0][j] = 0;
    }

    for (int i = 1; i < n + 1; i++) {
      for (int j = 1; j < rodlength + 1; j++) {
        int l = length[i - 1];
        int p = price[i - 1];
        if (l <= j) {
          int include = p + dp[i][j - l];
          int exclude = dp[i - 1][j];
          dp[i][j] = Math.max(include, exclude);
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }
    return dp[n][rodlength];
  }

  public static void main(String[] args) {
    int length[] = { 1, 2, 3, 4, 5, 6, 7, 8 };
    int price[] = { 1, 5, 8, 9, 10, 17, 17, 20 };
    int rodLength = 8;
    System.out.println(maxProfit(length, price, rodLength));
  }
}
