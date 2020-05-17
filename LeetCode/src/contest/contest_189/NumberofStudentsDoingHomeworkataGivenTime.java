package contest.contest_189;

/**
 * Create by haifei on 17/5/2020 10:35 AM.
 */
public class NumberofStudentsDoingHomeworkataGivenTime {

    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {

        int result = 0;
        for (int i = 0; i < startTime.length; i++) {
            if (queryTime <= endTime[i] && queryTime >= startTime[i]) {
                result++;
            }
        }

        return result;
    }
}
