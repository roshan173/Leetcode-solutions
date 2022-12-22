/*
 * @lc app=leetcode id=1054 lang=java
 *
 * [1054] Distant Barcodes
 *
 * https://leetcode.com/problems/distant-barcodes/description/
 *
 * algorithms
 * Medium (45.73%)
 * Likes:    1014
 * Dislikes: 41
 * Total Accepted:    34.4K
 * Total Submissions: 75.2K
 * Testcase Example:  '[1,1,1,2,2,2]'
 *
 * In a warehouse, there is a row of barcodes, where the i^th barcode is
 * barcodes[i].
 * 
 * Rearrange the barcodes so that no two adjacent barcodes are equal. You may
 * return any answer, and it is guaranteed an answer exists.
 * 
 * 
 * Example 1:
 * Input: barcodes = [1,1,1,2,2,2]
 * Output: [2,1,2,1,2,1]
 * Example 2:
 * Input: barcodes = [1,1,1,1,2,2,3,3]
 * Output: [1,3,1,3,1,2,1,2]
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= barcodes.length <= 10000
 * 1 <= barcodes[i] <= 10000
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int code: barcodes) {
            map.merge(code, 1, Integer::sum);
            if (map.get(code) > map.getOrDefault(max, 0)) {
                max = code;
            }
        }
        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list, (a, b) -> map.get(b) - map.get(a));
        int n = barcodes.length;
        int[] res = new int[n];
        int idx = 0;
        for (int i=0; i<n; i+=2) {
            res[i] = list.get(idx);
            map.merge(list.get(idx), -1, Integer::sum);
            if (map.get(list.get(idx)) == 0) {
                map.remove(list.get(idx));
                idx++;
            }
        }

        for (int i=1; i<n; i+=2) {
            res[i] = list.get(idx);
            map.merge(list.get(idx), -1, Integer::sum);
            if (map.get(list.get(idx)) == 0) {
                map.remove(list.get(idx));
                idx++;
            }
        }
        
        return res;
    }
}
// @lc code=end
