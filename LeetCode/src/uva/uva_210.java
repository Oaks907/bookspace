package uva;

import java.util.ArrayDeque;

import org.junit.Test;

/**
 * Create by haifei on 18/9/2020 12:25 PM.
 */
public class uva_210 {

    int quantum;
    int[] var = new int[26]; // 存储变量的赋值
    boolean locked = false; // 锁
    ArrayDeque<Integer> readyQueue = new ArrayDeque<>(); // 就绪队列
    ArrayDeque<Integer> blockQueue = new ArrayDeque<>(); // 阻塞队列

    /**
     * @param started  不同并行程序当前执行到的代码行号
     * @param costTime 语句花费时间
     * @param programs 程序语句
     * @param pid      并行程序序号
     */
    public void run(int[] started, int[] costTime, String[] programs, int pid) {
        int q = quantum;

        while (q > 0) {
            String program = programs[started[pid]];

            String[] split = program.split(" ");

            switch (split[0]) {
                case "print":
                    // 打印
                    int index = split[1].charAt(0) - 'a';
                    System.out.println(var[index]);
                    q -= costTime[1];
                    break;
                case "lock":
                    if (locked) {
                        blockQueue.add(pid);
                        return;
                    }
                    locked = true;
                    q -= costTime[2];
                    break;
                case "unlock":
                    locked = false;
                    if (!blockQueue.isEmpty()) {
                        // 添加到就绪队列首部
                        readyQueue.addFirst(blockQueue.pop());
                    }
                    q -= costTime[3];
                    break;
                case "end":
                    return;
                default:
                    if ("=".equals(split[1])) {
                        // 赋值语句，存储赋值
                        var[split[0].charAt(0) - 'a'] = Integer.parseInt(split[2]);
                        q -= costTime[0];
                    }
                    break;
            }

            started[pid]++;
        }

        readyQueue.add(pid);
    }

    public void entrance(int exeNum, int quantum, int[] costTime, String[] programs) {
        this.quantum = quantum;

        int[] started = new int[exeNum];
        int line = 0;

        for (int i = 0; i < exeNum; i++) {
            // 记录每个程序的开始执行代码序号
            started[i] = line++;

            for (int j = 0; j < programs.length; j++) {
                String[] split = programs[j].split(" ");
                if (split.length == 1 && "end".equals(split[0])) {
                    break;
                }
                line++;
            }
            // 记录就绪程序序号
            readyQueue.add(i);
        }

        while (!readyQueue.isEmpty()) {
            int pidd = readyQueue.pop();
            run(started, costTime, programs, pidd);
        }
    }

    @Test
    public void test() {
        int[] costTime = new int[5];

        for (int i = 0; i < costTime.length; i++) {
            costTime[i] = 1;
        }

        String[] programs =
                {"a = 4", "print a", "lock", "b = 9", "print b", "unlock", "print b", "end", "a = 3", "print a", "lock",
                        "b = 8", "print b", "unlock", "print b", "end", "b = 5", "a = 17", "print a", "print b", "lock",
                        "b = " + "21", "print b", "unlock", "print b", "end"};
        entrance(3, 1, costTime, programs);
    }
}
