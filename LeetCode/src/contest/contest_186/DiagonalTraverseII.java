package contest.contest_186;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 26/4/2020 9:55 PM.
 */
public class DiagonalTraverseII {

    // TLE
    public int[] findDiagonalOrder_TLE(List<List<Integer>> nums) {

        List<Integer> res = new ArrayList<>();
        int maxRow = nums.size();
        int maxCol = 0;
        for (List<Integer> num : nums) {
            maxCol = Math.max(num.size(), maxCol);
        }

        int startRow = 0;
        int startCol = 0;

        while (true) {
            //            System.out.println(startRow + " col: " + startCol);

            int row = startRow;
            int col = startCol;

            while (row >= 0 && col < maxCol) {
                //                System.out.println(row + " " + col);
                List<Integer> item = nums.get(row);
                if (item.size() > col) {
                    res.add(item.get(col));
                }
                row--;
                col++;
            }

            if (startRow == maxRow - 1 && startCol == maxCol - 1) {
                break;
            }

            if (startRow < maxRow - 1) {
                startRow++;
            } else {
                if (startCol < maxCol - 1) {
                    startCol++;
                }
            }
        }

        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }

    public int[] findDiagonalOrder(List<List<Integer>> nums) {

        Map<Integer, List<Integer>> map = new HashMap<>();
        int size = 0;
        int maxKey = 0;

        for (int row = nums.size() - 1; row >= 0; row--) {
            for (int col = 0; col < nums.get(row).size(); col++) {
                map.putIfAbsent(row + col, new ArrayList<>());
                map.get(row + col).add(nums.get(row).get(col));
                maxKey = Math.max(row + col, maxKey);
                size++;
            }
        }
        
        int[] res = new int[size];
        int index = 0;

        for (int i = 0; i <= maxKey; i++) {
            List<Integer> list = map.get(i);
            if (list == null) continue;
            for (int v : list) res[index++] = v;
        }

        return res;
    }

    @Test
    public void test() {
        List<List<Integer>> nums = new ArrayList<>();
        nums.add(Arrays.asList(1, 2, 3, 4, 5));
        nums.add(Arrays.asList(6, 7));
        nums.add(Arrays.asList(8));
        nums.add(Arrays.asList(9, 10, 11));
        nums.add(Arrays.asList(12, 13, 14, 15, 16));

        // [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
        // 1, 6, 2, 8, 7, 3, 9, 4, 12, 10, 5, 13, 11, 14, 15, 16
        int[] res = findDiagonalOrder(nums);

        PrintUtils.printArray(res);
    }
}
