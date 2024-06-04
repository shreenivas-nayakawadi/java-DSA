// for the given array of numbers find the subset whose sum is 10
// numbers[] = {4, 2, 7, 1, 3} // similar to val and wt i.e val = wt = numbers
// TargetSum = 10 //similar to knapsack weight
// similar to the knapsack 0_1 tabulation

package DP;

public class TargetSumSubset {

  public static boolean isSubSet(int arr[], int sum) {
    int n = arr.length;
    boolean dp[][] = new boolean[n + 1][sum + 1]; // creating 2d array and by default all values will be false
    // in 2d array i ==> i items , j ==> targetSum
    // initialization
    for (int i = 0; i < n + 1; i++) {
      dp[i][0] = true;
    }

    for (int i = 1; i < n + 1; i++) {
      for (int j = 1; j < sum + 1; j++) {
        int v = arr[i - 1];
        //include
        if (v <= j && dp[i - 1][j - v] == true) { // valid i.e value is less than targetsum j and for (i -1 )items and target sum (j - v) also forms subset, (j - v) bcoz we included v
          dp[i][j] = true;
        }
        // exclude
        else if (dp[i - 1][j] == true) { // for (i -1 )items and target sum (j) also forms subset
          dp[i][j] = true;
        }
      }
    }
    print(dp);
    return dp[n][sum]; // dp[n][sum] bcoz we are finding subsets for targetSum=> sum and n items
  }

  public static void print(boolean arr[][]) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        System.out.print(arr[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  public static void main(String[] args) {
    int numbers[] = { 4, 2, 7, 1, 3 };
    int TargetSum = 10;
    System.out.println(isSubSet(numbers, TargetSum));
  }
}
