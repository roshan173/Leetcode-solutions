class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int sum = 0;
        int n = grumpy.length;
        for (int i=0; i<n; i++) {
            if (grumpy[i] == 0) sum += customers[i];
        }
        int maxSum = 0, curSum = 0;
        for (int i=0; i<n; i++) {
            if (grumpy[i] == 1) curSum += customers[i];
            if (i >= minutes && grumpy[i - minutes] == 1) {
                curSum -= customers[i - minutes];
            } 
            maxSum = Math.max(maxSum, curSum);
        }
        return sum + maxSum;
    }
}

/*
Intution: ans = customers entering at non grumpy minutes 
                + max subarray sum of length minutes (counting customers at grumpy minutes);

 */
