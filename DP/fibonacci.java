//memoization: storing the repeating values/functions in f[] also called as DP array
// in recursion concept while calculating  fibonacci , we calculated fibonacci of 2, ,3,4,5 etc are calculated multiple tines
// in DP instead of calculating it in multiple times , we store that fibonacci in an array and whenever it call for fib(2) or anything instead of calculating using function we access it form the array
package DP;

public class fibonacci {

  //O(2^n)
  public static int fib2(int n) { // using recursion method
    if (n == 0 || n == 1) {
      return n;
    }
    return fib2(n - 1) + fib2(n - 2);
  }

  //O(n)
  public static int fib(int n, int f[]) { // using memoization method
    if (n == 0 || n == 1) {
      return n;
    }
    if (f[n] != 0) { // checking whether fib is calculated or not , if value is 0, it means not calculated yet, bcoz by default array stores values as zero, once we calculate fib[n] we store it in the array
      return f[n];
    }
    f[n] = fib(n - 1, f) + fib(n - 2, f);
    return f[n];
  }

  public static int fib1(int n) { // using tabulation method
    int dp[] = new int[n + 1];
    dp[0] = 0;
    dp[1] = 1;
    for (int i = 2; i <= n; i++) {
      dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];
  }

  public static void main(String[] args) {
    int n = 5;
    int f[] = new int[n + 1];
    System.out.println(fib(5, f));
    System.out.println(fib1(5));
    System.out.println(fib2(5));
  }
}
