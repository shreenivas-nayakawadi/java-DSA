// knapsack is basically a bag or a pouch where we can store items
// (value of the items) 𝘃𝗮𝗹[]= 𝟭𝟱, 𝟭𝟰, 𝟭𝟬, 𝟰𝟱, 𝟯𝟬
// (weight of the corresponding items) 𝘄𝘁[]= 𝟮, 𝟱, 𝟭, 𝟯, 𝟰  ==> (condition 1 satisfied: choice available)
// weight capacity (i.e maximum weight that knapsack can hold) 𝗪(𝘁𝗼𝘁𝗮𝗹 𝗮𝗹𝗹𝗼𝘄𝗲𝗱 𝘄𝗲𝗶𝗴𝗵𝘁) = 𝟳
// (choose theitems in such a way that we get maximum profit), 𝗮𝗻𝘀 = 𝗺𝗮𝘅 𝗽𝗿𝗼𝗳𝗶𝘁 ==> (condition 2 satisfied: optimal solution)

//===========================================================
// recursion
// base case : if weight == 0 or i == 0 i.e no elements remaining
// traverse from last element to first element
// check whether item weight less than knapsack weight, if its less than either add it to knap sack or exclude
// if its greater than exclude it from the knapsack
package DP;

public class knapsack_0_1 {

  // 𝗿𝗲𝗰𝘂𝗿𝘀𝗶𝗼𝗻 𝗺𝗲𝘁𝗵𝗼𝗱 O(n^2)
  public static int knapsack(int val[], int wt[], int W, int n) {
    if (W == 0 || n == 0) { // no items remained or knapsack is full i.e W = 0
      return 0; // no possible ways so max profit is 0 so return it
    }
    if (wt[n - 1] <= W) {
      // include
      int ans1 = val[n - 1] + knapsack(val, wt, W - wt[n - 1], n - 1); // adding value of current item to the ans and recursively calling the function for remaining elements to fetech the ans
      // exclude
      int ans2 = knapsack(val, wt, W, n - 1); // just skipping the current item and fetch the ans for remaining items
      // find max among included and excluded
      return Math.max(ans1, ans2); // in which of the above condition we get max value (i.e by rejecting or accepting the item for knapsack), we return that value bcoz we need maximum profit
    } else {
      return knapsack(val, wt, W, n - 1); // ince the weight of item is less than the weight of the kanpsack so we exclude the current item and fetch the value for the remaining items
    }
  }

  //========================================================================
  // 𝘂𝘀𝗶𝗻𝗴 𝗺𝗲𝗺𝗼𝗶𝘇𝗮𝘁𝗶𝗼𝗻 𝗺𝗲𝘁𝗵𝗼𝗱
  // since two variables are changing in the recursion i.e weight W and n
  // we create 2d matrix where we have n+1 rows(row 0 is 0 items available, row 1 is 1 item available, row 2 is 2 items available..... row n is n items available) and W+1 columns(weight W ==> W = 0 col, W = 1kg knapsack weight col, W = 2kg knapsack weight col, .... W = Wkg knapsack weight col) , added 1 to both becoz we include 0 for base cases(0 items and 0 weight)
  // analyze for all possible weights find maximum profit by choosing among n available items i.e find max profit for weight =1kg x available items, similarly for 2kg y available items....... for n kg z available items (basically find the profits and store in array, so that we should not find the max profit fot the same weight again and again)
  // for example: if i = 3 and j = 5, it means only 3 items are available and max weight that can be hold by knapsack is 5kg , ==> in the position (3, 5) we store the max profit of 3 items available and 5kg knapsack weight

  //O(nW)
  public static int knapsack1(int val[], int wt[], int W, int n, int dp[][]) {
    if (W == 0 || n == 0) { // no items remained or knapsack is full i.e W = 0
      return 0; // no possible ways so max profit is 0 so return it
    }
    if (dp[n][W] != -1) { // check possible ways is already calculated, if its calculated (not equal to -1) return its value
      return dp[n][W];
    }
    if (wt[n - 1] <= W) {
      //include
      int ans1 = val[n - 1] + knapsack1(val, wt, W - wt[n - 1], n - 1, dp); // same as above recursion
      //exclude
      int ans2 = knapsack1(val, wt, W, n - 1, dp); // same as above recursion
      // find max among included and excluded
      dp[n][W] = Math.max(ans1, ans2); // store the value and then return
      return dp[n][W];
    } else {
      dp[n][W] = knapsack1(val, wt, W, n - 1, dp); // store the value and then return
      return dp[n][W];
    }
  }

  //========================================================================
  // 𝘂𝘀𝗶𝗻𝗴 𝘁𝗮𝗯𝘂𝗹𝗮𝘁𝗶𝗼𝗻 𝗺𝗲𝘁𝗵𝗼𝗱
  // 1.create table
  // 2.assing meaning
  // 3.fill in bottom up manner( first find small and then large)
  //4. initialization base case
  public static int knapsack2(int val[], int wt[], int W) {
    int n = val.length;
    int dp1[][] = new int[n + 1][W + 1];

    for (int i = 0; i < n; i++) { // 0th col
      dp1[i][0] = 0; // initializing 0 for weight = 0
    }

    for (int j = 0; j < dp1[0].length; j++) { // 0th row
      dp1[0][j] = 0; // initializing 0 for items = 0
    }

    for (int i = 1; i < n + 1; i++) { // both starting from 2 bcoz beofre we initalized 0th row and 0th col
      for (int j = 1; j < W + 1; j++) {
        int v = val[i - 1]; // both i-1 bcoz  since we added (n + 1 and W + 1) in dp so ith value in dp1 = i-1th value in val, same for the wt
        int w = wt[i - 1];
        if (w <= j) { // valid condition {by considering dp1 here j is allowed weight W of the knapsack}
          // include
          int incProfit = v + dp1[i - 1][j - w]; // value v +  possible max profit of i - 1 elements and j - w knapsack weight

          // exclude
          int excProfit = dp1[i - 1][j]; // by skipping the current element  and calculate possible max profit of i - 1 elements and j knapsack weight
          // storing max profit for current i and j
          dp1[i][j] = Math.max(incProfit, excProfit);
        } else { // invalid
          int excProfit = dp1[i - 1][j];
          dp1[i][j] = excProfit;
        }
      }
    }

    // printing values
    System.out.println(
      "printing all the possible values of max profit considering i items and j weight"
    );
    for (int i = 0; i < dp1.length; i++) {
      for (int j = 0; j < dp1[0].length; j++) {
        System.out.print(dp1[i][j] + " ");
      }
      System.out.println();
    }

    return dp1[n][W]; // gives last row last col value , bcoz last rowth value n (i.e n items available)  and last col th value is W (i.e allowd weight of knapsack)
  }

  //===================================================================

  public static void main(String[] args) {
    int W = 7;
    int val[] = { 15, 14, 10, 45, 30 };
    int wt[] = { 2, 5, 1, 3, 4 };
    int n = val.length;
    System.out.println(knapsack(val, wt, W, n));

    int dp[][] = new int[n + 1][W + 1];
    for (int i = 0; i < dp.length; i++) {
      for (int j = 0; j < dp[0].length; j++) {
        dp[i][j] = -1; /// initializing all the values of dp t0 -1 ==> no possible profit is calculated
      }
    }
    System.out.println(knapsack1(val, wt, W, n, dp));
    System.out.println(knapsack2(val, wt, W));
  }
}

/* only code
 package DP;
public class knapsack_0_1 {

  // 𝗿𝗲𝗰𝘂𝗿𝘀𝗶𝗼𝗻 𝗺𝗲𝘁𝗵𝗼𝗱 O(n^2)
  public static int knapsack(int val[], int wt[], int W, int n) {
    if (W == 0 || n == 0) {
      return 0;
    }
    if (wt[n - 1] <= W) {
      int ans1 = val[n - 1] + knapsack(val, wt, W - wt[n - 1], n - 1); 
      int ans2 = knapsack(val, wt, W, n - 1); 
      return Math.max(ans1, ans2); 
    } else {
      return knapsack(val, wt, W, n - 1); 
    }
  }

  // 𝘂𝘀𝗶𝗻𝗴 𝗺𝗲𝗺𝗼𝗶𝘇𝗮𝘁𝗶𝗼𝗻 𝗺𝗲𝘁𝗵𝗼𝗱
  //O(nW)
  public static int knapsack1(int val[], int wt[], int W, int n, int dp[][]) {
    if (W == 0 || n == 0) { 
      return 0; 
    }
    if (dp[n][W] != -1) { 
      return dp[n][W];
    }
    if (wt[n - 1] <= W) {
      int ans1 = val[n - 1] + knapsack1(val, wt, W - wt[n - 1], n - 1, dp); 
      int ans2 = knapsack1(val, wt, W, n - 1, dp);
      dp[n][W] = Math.max(ans1, ans2); 
      return dp[n][W];
    } else {
      dp[n][W] = knapsack1(val, wt, W, n - 1, dp); 
      return dp[n][W];
    }
  }

  // 𝘂𝘀𝗶𝗻𝗴 𝘁𝗮𝗯𝘂𝗹𝗮𝘁𝗶𝗼𝗻 𝗺𝗲𝘁𝗵𝗼𝗱
  public static int knapsack2(int val[], int wt[], int W) {
    int n = val.length;
    int dp1[][] = new int[n + 1][W + 1];
    for (int i = 0; i < n; i++) {
      dp1[i][0] = 0; 
    }
    for (int j = 0; j < dp1[0].length; j++) { 
      dp1[0][j] = 0; 
    }
    for (int i = 1; i < n + 1; i++) { 
      for (int j = 1; j < W + 1; j++) {
        int v = val[i - 1]; 
        int w = wt[i - 1];
        if (w <= j) { 
          int incProfit = v + dp1[i - 1][j - w]; 
          int excProfit = dp1[i - 1][j]; 
          dp1[i][j] = Math.max(incProfit, excProfit);
        } else { 
          int excProfit = dp1[i - 1][j];
          dp1[i][j] = excProfit;
        }
      }
    }
    System.out.println("printing all the possible values of max profit considering i items and j weight");
    for (int i = 0; i < dp1.length; i++) {
      for (int j = 0; j < dp1[0].length; j++) {
        System.out.print(dp1[i][j] + " ");
      }
    System.out.println();
    }
    return dp1[n][W]; 
  }
  
  public static void main(String[] args) {
    int W = 7;
    int val[] = { 15, 14, 10, 45, 30 };
    int wt[] = { 2, 5, 1, 3, 4 };
    int n = val.length;
    System.out.println(knapsack(val, wt, W, n));

    int dp[][] = new int[n + 1][W + 1];
    for (int i = 0; i < dp.length; i++) {
      for (int j = 0; j < dp[0].length; j++) {
        dp[i][j] = -1; 
      }
    }
    System.out.println(knapsack1(val, wt, W, n, dp));
    System.out.println(knapsack2(val, wt, W));
  }
}
 */
