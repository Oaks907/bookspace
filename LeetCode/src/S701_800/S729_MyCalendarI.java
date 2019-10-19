package S701_800;

import java.util.TreeMap;

/**
 * Create by haifei on 18/10/2019 8:27 AM.
 */
public class S729_MyCalendarI {

  TreeMap<Integer, Integer> meeting;

  public S729_MyCalendarI() {
    meeting = new TreeMap<>();
  }

  public boolean book(int start, int end) {
    Integer lowerKey = meeting.lowerKey(start);
    Integer upperKey = meeting.ceilingKey(start);

    if (lowerKey != null && !(start >= meeting.get(lowerKey))) {
      return false;
    }
    if (upperKey != null && !(end <= upperKey)) {
      return false;
    }

    meeting.put(start, end);

    return true;
  }
}
