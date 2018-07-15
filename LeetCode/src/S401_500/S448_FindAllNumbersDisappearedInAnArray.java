package S401_500;

import java.util.*;

/**
 * Create by haifei on 15/1/2018.
 */
public class S448_FindAllNumbersDisappearedInAnArray {
  public static List<Integer> findDisappearedNumbers(int[] nums) {
    ArrayList<Integer> result = new ArrayList<>();
    Set<Integer> hasSet = new HashSet<>();
    int minValue = Integer.MAX_VALUE;
    for (int i = 0; i < nums.length; i++) {
      hasSet.add(nums[i]);
      minValue = Math.min(minValue, nums[i]);
    }
    for (int i = 0; i < nums.length; i++) {
      boolean containBool = hasSet.contains(minValue);
      if (!containBool) {
        result.add(minValue);
      }
      minValue++;
    }
    return result;
  }

  public static List<Integer> findDisappearedNumbers2(int[] nums) {
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      int val = Math.abs(nums[i]) - 1;
      if (nums[val] > 0){
        nums[val] = -nums[val];
      }
    }
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > 0) {
        result.add(i + 1);
      }
    }
    return result;
  }


  public static void main(String[] args) {
    int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
    System.out.println(findDisappearedNumbers(nums));
    System.out.println(findDisappearedNumbers2(nums));
  }
}
