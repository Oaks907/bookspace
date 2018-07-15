package S101_200;

import java.util.logging.Level;

/**
 * Create by haifei on 3/1/2018.
 */
public class S167_InputArrayIsSorted {

  public int[] twoSum(int[] numbers, int target) {
    int result[] = new int[2];
    int first = 0;
    int last = numbers.length - 1;

    while (first < last) {
      int temp = numbers[first] + numbers[last];
      if (temp == target) {
        result[0] = first + 1;
        result[1] = last + 1;
        break;
      } else if (temp > target) {
        last--;
      } else {
        first++;
      }
    }
    return result;
  }
}
