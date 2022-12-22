class Solution {
    public int minimumLines(int[][] prices) {
        Arrays.sort(prices, (a, b) -> a[0]-b[0]);        
        int count = 0;
        if (prices.length > 1) count++;
        for (int i=1; i<prices.length-1; i++) {
            int[] p1 = prices[i-1];
            int[] p2 = prices[i];
            int[] p3 = prices[i+1];
            if ((p3[1] - p2[1]) * (p2[0] - p1[0]) != (p3[0] - p2[0]) * (p2[1] - p1[1])) count++;
        }
        return count;
    }
}

/*

for 3 points: 
we need only one line if the slope between p1 - p2 = p2 - p3
y3 - y2       y2 - y1
---------  =  -------
x3 - x2       x2 - x1


Intution:
[[3,4],[1,2],[7,8],[2,3]]
-> sort the stockPrices by day - [[1,2], [2,3], [3,4], [7,8]]
-> if : there is only one day - no line is required
   else : compare slope of i - 1, i with slope of i, i + 1
            if they are not the same, increase count by one;
 */

/*
Something to remember: 
To check -> A/B == C/D
int g1 = gcd(A,B), g2 = gcd(C,D);
check -> (A/g1 == C/g2 && B/g1==D/g2)
If this condition is true means the [ A/B == C/D ] 
-> This implementation will never give error for division comparison.
*/



/*
//previous approach

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
*/

