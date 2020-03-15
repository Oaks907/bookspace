package contest.contest_180;

import utils.PrintUtils;

/**
 * Create by haifei on 15/3/2020 10:49 AM.
 */
public class CustomStack {

    int[] arr;
    int index = 0;
    int maxSize = 0;

    public CustomStack(int maxSize) {
        arr = new int[maxSize];
        this.maxSize = maxSize;
    }

    public void push(int x) {
        if (index >= maxSize) {
            return;
        }
        arr[index++] = x;
    }

    public int pop() {
        if (index <= 0) {
            return -1;
        }

        int res = arr[--index];
        arr[index] = 0;
        return res;
    }

    public void increment(int k, int val) {
        if (k > index) {
            k = index;
        }

        for (int i = 0; i < k; i++) {
            arr[i] += val;
        }
    }

    public static void main(String[] args) {
        CustomStack customStack = new CustomStack(3);
        customStack.push(1);
        customStack.push(2);
        System.out.println(customStack.pop());

        PrintUtils.printArray(customStack.arr);

        customStack.push(2);    // stack becomes [1, 2]
        customStack.push(3);   // stack becomes [1, 2, 3]
        customStack.push(4);    // stack still [1, 2, 3], Don't add another elements as size is 4

        PrintUtils.printArray(customStack.arr);

        customStack.increment(5, 100);                // stack becomes [101, 102, 103]

        PrintUtils.printArray(customStack.arr);

        customStack.increment(2, 100);                // stack becomes [201, 202, 103]

        PrintUtils.printArray(customStack.arr);

        // 103
        System.out.println(customStack.pop());
        // 202
        System.out.println(customStack.pop());
        // 201
        System.out.println(customStack.pop());
        // -1
        System.out.println(customStack.pop());

    }
}
