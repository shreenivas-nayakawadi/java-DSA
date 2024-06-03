// count ways to reach the nth stair. The person can climb either 1 or 2 stairs at a time

// no of ways to reach n steps = no of steps to reach (n-1) + no of steps to reach (n-2)
// i.e  when we reach (n-1)th step we just need one step to reach nth step (cross 1 stair at a time), when we reach (n-2)th step we just need two step to reach nth step (cross 2 stair at a time) just add both of them to get number of ways to reach n steps

package DP;

import java.util.Arrays;

public class climbingStairs {

  //O(2^n)
  public static int ways(int n) { // recursion
    if (n == 0) {
      return 1;
    }
    if (n < 0) {
      return 0;
    }
    return ways(n - 1) + ways(n - 2);
  }

  //O(n)
  public static int ways1(int n, int dp[]) { // memoization
    if (n == 0) {
      return 1;
    }
    if (n < 0) {
      return 0;
    }
    if (dp[n] != -1) {
      return dp[n];
    }
    dp[n] = ways1(n - 1, dp) + ways1(n - 2, dp);
    return dp[n];
  }

  //O(n)
  public static int ways2(int n) { // tabulation
    int dp1[] = new int[n + 1];
    dp1[0] = 1; // ways to reach 0
    //tabulation loop
    for (int i = 1; i <= n; i++) {
      if (i == 1) {
        dp1[i] = dp1[i - 1] + 0;
      } else {
        dp1[i] = dp1[i - 1] + dp1[i - 2];
      }
    }
    return dp1[n];
  }

  // when 1stair 2stair and 3stair at a time is allowed
  // no of ways to reach n steps = no of steps to reach (n-1) + no of steps to reach (n-2) + no of steps to reach (n-3)
  public static void main(String[] args) {
    int n = 5;
    int dp[] = new int[n + 1];
    Arrays.fill(dp, -1);
    System.out.println(ways(n));
    System.out.println(ways1(n, dp));
    System.out.println(ways2(n));
  }
}
