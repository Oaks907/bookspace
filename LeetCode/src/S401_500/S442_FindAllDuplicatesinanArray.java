package S401_500;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by haifei on 28/8/2019 12:22 AM.
 */
public class S442_FindAllDuplicatesinanArray {

  public List<Integer> findDuplicates(int[] nums) {
    List<Integer> result = new ArrayList<>();

    for (Integer item : nums) {
      int index = Math.abs(item) - 1;
      if (nums[index] < 0) {
        result.add(index + 1);
      }
      nums[index] = -nums[index];
    }

    return result;
  }

  public static void main(String[] args) {
    final S442_FindAllDuplicatesinanArray duplicatesinanArray =
      new S442_FindAllDuplicatesinanArray();

    int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
    System.out.println(duplicatesinanArray.findDuplicates(nums));
  }
}
