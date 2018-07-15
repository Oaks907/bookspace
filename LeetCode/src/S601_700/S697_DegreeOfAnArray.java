package S601_700;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by haifei on 11/1/2018.
 */
public class S697_DegreeOfAnArray {
  public static int findShortestSubArray(int[] nums) {
    HashMap<Integer, Integer> map = new HashMap<>();
    HashMap<Integer, Integer[]> valMap = new HashMap<>();
    int degress = 0, minSize = nums.length;

    for (int i = 0; i < nums.length; i++) {
      map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
      degress = Math.max(degress, map.get(nums[i]));

      if (!valMap.containsKey(nums[i])){
        valMap.put(nums[i], new Integer[2]);
      }
      Integer[] numRange = valMap.get(nums[i]);
      if (numRange[0] == null) {
        numRange[0] = i;
      }
      numRange[1] = i;
    }

    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      if (entry.getValue() < degress) {
        continue;
      }
      Integer[] numRange= valMap.get(entry.getKey());
      minSize = Math.min(minSize, (numRange[1] - numRange[0]) + 1);
    }

    return minSize;
  }

  public static void main(String[] args) {
    int []nums = {1,2,2,3,1};
    System.out.println(findShortestSubArray(nums));
  }

}
