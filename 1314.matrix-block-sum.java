/*
 * @lc app=leetcode id=1314 lang=java
 *
 * [1314] Matrix Block Sum
 *
 * https://leetcode.com/problems/matrix-block-sum/description/
 *
 * algorithms
 * Medium (75.37%)
 * Likes:    2004
 * Dislikes: 314
 * Total Accepted:    72.2K
 * Total Submissions: 95.8K
 * Testcase Example:  '[[1,2,3],[4,5,6],[7,8,9]]\n1'
 *
 * Given a m x n matrix mat and an integer k, return a matrix answer where each
 * answer[i][j] is the sum of all elements mat[r][c] for:
 * 
 * 
 * i - k <= r <= i + k,
 * j - k <= c <= j + k, and
 * (r, c) is a valid position in the matrix.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]], k = 1
 * Output: [[12,21,16],[27,45,33],[24,39,28]]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]], k = 2
 * Output: [[45,45,45],[45,45,45],[45,45,45]]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n, k <= 100
 * 1 <= mat[i][j] <= 100
 * 
 * 
 */

// @lc code=start
class Solution {

    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (i==0 && j==0) continue;
                if (i==0) mat[i][j] += mat[i][j-1];
                else if (j==0) mat[i][j] += mat[i-1][j];
                else {
                    mat[i][j] += mat[i-1][j] + mat[i][j-1] - mat[i-1][j-1];
                }
            }
        }

        int[][] res = new int[m][n];
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                int r1 = Math.max(i-k, 0);
                int c1 = Math.max(j-k, 0);
                int r2 = Math.min(i+k, m-1);
                int c2 = Math.min(j+k, n-1);
                res[i][j] = mat[r2][c2];
                if (r1 > 0) res[i][j] -= mat[r1-1][c2];
                if (c1 > 0) res[i][j] -= mat[r2][c1-1];
                if (r1 > 0 && c1 > 0) res[i][j] += mat[r1-1][c1-1];
            }
        }

        return res;
    }
}
// @lc code=end
/*
 * Intial thought: [1, 1, 1, 1, 1]
 *                 [1, 1, 1, 1, 1]
 *                 [1, 1, 1, 1, 1]
 *                 [1, 1, 1, 1, 1]
 *                 [1, 1, 1, 1, 1]
 * 
 * 
 * sum =           [1,  2,  3,  4,  5]
 *                 [2,  4,  6,  8, 10]
 *                 [3,  6,  9, 12, 15]
*                  [4,  8, 12, 16, 20]
 *                 [5, 10, 15, 20, 25]
 * 
 */