package bweekly.contest_42;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 26/12/2020 10:32 PM.
 */
public class NumberofStudentsUnabletoEatLunch {

    public int countStudents(int[] students, int[] sandwiches) {
        Queue<Integer> stuQueue = new LinkedList();
        Queue<Integer> sandQueue = new LinkedList();

        int len = students.length;
        for (int i = 0; i < len; i++) {
            stuQueue.add(students[i]);
            sandQueue.add(sandwiches[i]);
        }

        while (!sandQueue.isEmpty()) {
            int retry = 0;
            int curStuSize = len;

            while (!stuQueue.isEmpty()) {
                Integer head = stuQueue.poll();
                if (head.equals(sandQueue.peek())) {
                    sandQueue.poll();
                    retry = 0;
                } else {
                    stuQueue.add(head);
                    retry++;
                }
                if (retry > curStuSize && !sandQueue.isEmpty()) {
                    System.out.println(curStuSize);
                    System.out.println(sandQueue);
                    return stuQueue.size();
                }
            }
        }

        return 0;
    }

    @Test
    public void test() {
        int[] stu = {1, 1, 1, 0, 0, 1};
        int[] sand = {1, 0, 0, 0, 1, 1};

        int result = countStudents(stu, sand);

        Assert.assertEquals(3, result);
    }

    @Test
    public void test1() {
        int[] stu = {0,0,0,1,1,1,1,0,0,0};
        int[] sand = {1,0,1,0,0,1,1,0,0,0};

        int result = countStudents(stu, sand);

        Assert.assertEquals(0, result);
    }
}
