package contest.contest_182;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * Create by haifei on 29/3/2020 10:45 AM.
 */
public class UndergroundSystem {

    Map<String, Map<Integer, List<PersonInfo>>> inMap = new HashMap<>();
    Map<Integer, PersonInfo> idMap = new HashMap<>();
    Map<String, Map<Integer, List<PersonInfo>>> outMap = new HashMap<>();

    public UndergroundSystem() {

    }

    public void checkIn(int id, String stationName, int t) {
        PersonInfo personInfo = new PersonInfo();
        personInfo.id = id;
        personInfo.inStationName = stationName;
        personInfo.inTime = t;

        Map<Integer, List<PersonInfo>> orDefault = inMap.getOrDefault(stationName, new HashMap<>());
        List<PersonInfo> defaultOrDefault = orDefault.getOrDefault(t, new ArrayList<>());

        defaultOrDefault.add(personInfo);
        orDefault.put(id, defaultOrDefault);
        inMap.put(stationName, orDefault);

        idMap.put(id, personInfo);
    }

    public void checkOut(int id, String stationName, int t) {
        PersonInfo personInfo = idMap.get(id);
        if (personInfo == null) {
            return;
        }
        idMap.remove(id);
        personInfo.endStationName = stationName;
        personInfo.endTime = t;

        Map<Integer, List<PersonInfo>> orDefault = outMap.getOrDefault(stationName, new HashMap<>());

        List<PersonInfo> defaultOrDefault = orDefault.getOrDefault(id, new ArrayList<>());
        defaultOrDefault.add(personInfo);

        orDefault.put(id, defaultOrDefault);
        outMap.put(stationName, orDefault);
    }

    public double getAverageTime(String startStation, String endStation) {

        Map<Integer, List<PersonInfo>> inIdMap = inMap.get(startStation);
        if (inIdMap == null) {
            return 0;
        }
        Map<Integer, List<PersonInfo>> outIdMap = outMap.get(endStation);
        if (outIdMap == null) {
            return 0;
        }

        double count = 0;
        double sum = 0;

        for (Map.Entry<Integer, List<PersonInfo>> inEntry : inIdMap.entrySet()) {
            Integer id = inEntry.getKey();
            List<PersonInfo> inPersonInfoList = inEntry.getValue();
            List<PersonInfo> outPersonInfoList = outIdMap.getOrDefault(id, new ArrayList<>());

            for (int i = 0; i < Math.min(inPersonInfoList.size(), outPersonInfoList.size()); i++) {
                PersonInfo personInfo = inPersonInfoList.get(i);
                count++;
                sum += (personInfo.endTime - personInfo.inTime);
            }
            System.out.println(inPersonInfoList);
        }

        return count == 0 ? 0 : sum / count;
    }


    public class PersonInfo {
        int id;
        String inStationName;
        int inTime;
        String endStationName;
        int endTime;

        @Override
        public String toString() {
            return "PersonInfo{" + "id=" + id + ", inStationName='" + inStationName + '\'' + ", inTime=" + inTime
                           + ", endStationName='" + endStationName + '\'' + ", endTime=" + endTime + '}';
        }
    }

    @Test
    public void test() {
        UndergroundSystem undergroundSystem = new UndergroundSystem();
        undergroundSystem.checkIn(45, "Leyton", 3);
        undergroundSystem.checkIn(32, "Paradise", 8);
        undergroundSystem.checkIn(27, "Leyton", 10);
        undergroundSystem.checkOut(45, "Waterloo", 15);
        undergroundSystem.checkOut(27, "Waterloo", 20);
        undergroundSystem.checkOut(32, "Cambridge", 22);
        // 14.0
        System.out.println(undergroundSystem.getAverageTime("Paradise", "Cambridge"));
        // "Cambridge" (at time 22)
        // 11.0
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));
        // customer
        // with id=45 from time=3 to time=15 and a customer with id=27 from time=10 to time=20. So the average time
        // is ( (15-3) + (20-10) ) / 2 = 11.0
        undergroundSystem.checkIn(10, "Leyton", 24);
        // 11.0
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));
        undergroundSystem.checkOut(10, "Waterloo", 38);
        // 12.0
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));
    }
}
