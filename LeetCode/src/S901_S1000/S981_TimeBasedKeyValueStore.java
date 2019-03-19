package S901_S1000;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by haifei on 27/1/2019 10:52 AM.
 * https://leetcode.com/problems/time-based-key-value-store
 */
public class S981_TimeBasedKeyValueStore {

  /**
   * 时间超限版本
   */
//  private HashMap<String, HashMap<Integer, String>> map;
//
//  /**
//   * Initialize your data structure here.
//   */
//  public S981_TimeBasedKeyValueStore() {
//    map = new HashMap<>();
//  }
//
//  public void set(String key, String value, int timestamp) {
//    HashMap<Integer, String> timeMap;
//    if (map.containsKey(key)) {
//      timeMap = map.get(key);
//      timeMap.put(timestamp, value);
//    } else {
//      timeMap = new HashMap<>();
//      timeMap.put(timestamp, value);
//      map.put(key, timeMap);
//    }
//  }
//
//  public String get(String key, int timestamp) {
//    if (map.containsKey(key)) {
//
//      HashMap<Integer, String> timeMap = map.get(key);
//
//      int time = timestamp;
//
//      if (timeMap.containsKey(time)) {
//        return timeMap.get(time);
//      }
//
//      int maxTime = Integer.MIN_VALUE;
//      for (Map.Entry<Integer, String> entry : timeMap.entrySet()) {
//        int entryKey = entry.getKey();
//        if (entryKey < time && entryKey > maxTime) {
//          maxTime = entryKey;
//        }
//      }
//
//      return timeMap.getOrDefault(maxTime, "");
//    } else {
//      return "";
//    }
//  }


  private HashMap<String, List<Data>> map;

  /**
   * Initialize your data structure here.
   */
  public S981_TimeBasedKeyValueStore() {
    map = new HashMap<>();
  }

  public void set(String key, String value, int timestamp) {
    if (!map.containsKey(key)) map.put(key, new ArrayList<Data>());
    map.get(key).add(new Data(value, timestamp));
  }

  public String get(String key, int timestamp) {
    if (!map.containsKey(key)) return "";
    return binarySearch(map.get(key), timestamp);
  }

  protected String binarySearch(List<Data> list, int time) {
    int low = 0, high = list.size() - 1;
    while (low < high) {
      int mid = (low + high) >> 1;
      if (list.get(mid).time == time) return list.get(mid).val;
      if (list.get(mid).time < time) {
        if (list.get(mid+1).time > time) {
          return list.get(mid).val;
        }
        low = mid + 1;
      }
      else {
        high = mid -1;
      }
    }
    return list.get(low).time <= time ? list.get(low).val : "";
  }

  public class Data {
    String val;
    int time;

    Data(String val, int time) {
      this.val = val;
      this.time = time;
    }
  }

  public static void main(String[] args) {
    final S981_TimeBasedKeyValueStore kv =
      new S981_TimeBasedKeyValueStore();

    kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1
    System.out.println(kv.get("foo", 1));  // output "bar"
    System.out.println(kv.get("foo", 3)); // output "bar" since there is no value corresponding
    // to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"
    kv.set("foo", "bar2", 4);
    System.out.println(kv.get("foo", 4)); // output "bar2"
    System.out.println(kv.get("foo", 5)); //output "bar2"
  }
}
