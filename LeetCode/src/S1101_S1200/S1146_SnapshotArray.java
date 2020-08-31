package S1101_S1200;

import java.util.TreeMap;

/**
 * Create by haifei on 24/2/2020 9:58 PM.
 */
public class S1146_SnapshotArray {

    int snapId = 0;
    TreeMap<Integer, Integer>[] A;

    public S1146_SnapshotArray(int length) {
        A = new TreeMap[length];

        for (int i = 0; i < length; i++) {
            A[i] = new TreeMap<Integer, Integer>();
            A[i].put(0, 0);
        }
    }

    public void set(int index, int val) {
        A[index].put(snapId, val);
    }

    public int snap() {
        return snapId++;
    }

    public int get(int index, int snap_id) {

        return A[index].floorEntry(snap_id).getValue();
    }
}
