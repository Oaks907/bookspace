package S201_300;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Create by haifei on 29/1/2018.
 */
public class S219_containsNearbyDuplicate {

  //silly version
//  public static boolean containsNearbyDuplicate(int[] nums, int k) {
//    HashMap<Integer, Integer> hashMap = new HashMap<>();
//    for (int i = 0; i < nums.length; i++) {
//      int key = nums[i];
//      if (hashMap.containsKey(key) && i - hashMap.get(key) <= k) {
//        return true;
//      }
//      hashMap.put(key, i);
//    }
//    return false;
//  }

  public static boolean containsNearbyDuplicate(int[] nums, int k) {
    Set<Integer> set = new HashSet<>();

    for (int i = 0; i < nums.length; i++) {
      if (i > k){
        set.remove(nums[i - k - 1]);
      }
      if (!set.add(nums[i])) {
        return true;
      }
    }
    return false;
  }

  public static void main(String args[]) {

    int nums[] = {99, 99};
    System.out.println(containsNearbyDuplicate(nums, 2));
  }
}
