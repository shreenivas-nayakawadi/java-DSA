//memoization: storing the repeating values/functions in f[] also called as DP array
// in recursion concept while calculating  fibonacci , we calculated fibonacci of 2, ,3,4,5 etc are calculated multiple tines
// in DP instead of calculating it in multiple times , we store that fibonacci in an array and whenever it call for fib(2) or anything instead of calculating using function we access it form the array
package DP;

public class fibonacci { //O(n)

  public static int fib(int n, int f[]) {
    if (n == 0 || n == 1) {
      return n;
    }
    if (f[n] != 0) { // checking whether fib is calculated or not , if value is 0, it means not calculated yet, bcoz by default array stores values as zero, once we calculate fib[n] we store it in the array
      return f[n];
    }
    f[n] = fib(n - 1, f) + fib(n - 2, f);
    return f[n];
  }

  public static void main(String[] args) {
    int n = 5;
    int f[] = new int[n + 1];
    System.out.println(fib(5, f));
  }
}
