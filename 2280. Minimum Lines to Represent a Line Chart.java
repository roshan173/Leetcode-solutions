class Solution {
    public int minimumLines(int[][] stockPrices) {
        Arrays.sort(stockPrices, (a, b) -> a[0]-b[0]);        
        int count = 0;
        int[] prevSlope = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        for (int i=1; i<stockPrices.length; i++) {
            int[] curSlope = slope(stockPrices[i-1], stockPrices[i]);
            if (!Arrays.equals(prevSlope, curSlope)) {
                count++;
                prevSlope = curSlope;
            }
        }
        return count;
    }
    int[] slope(int[] a, int[] b) {
        int y = b[1] - a[1];
        int x = b[0] - a[0];
        int gcd = getGcd(y, x);
        y = y/gcd;
        x = x/gcd;
        return new int[]{x, y};
    }

    int getGcd(int a, int b) {
        if (a == 0) return b;
        return getGcd(b%a, a);
    }
}

/*
y = mx + c
       y2 - y1
 m  =  -------
       x2 - x1

Intution:
[[3,4],[1,2],[7,8],[2,3]]
-> sort the stockPrices by day - [[1,2], [2,3], [3,4], [7,8]]
-> if : there is only one day - no line is required
   else : calculate slope of i - 1, i and compare it with slope of i-2, i - 1
            if they are not the same, increase count by one;
Algo : 
    -> current_slope = MAX_VALUE;
    -> for i = 1 to n
    -> newSlope = calculate slope of i-1, i
    -> if newSlope != current_slope, ans++ and  current_slope = newSlope
    -> else do nothing
 */
