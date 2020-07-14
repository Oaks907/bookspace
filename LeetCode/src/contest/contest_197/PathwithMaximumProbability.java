package contest.contest_197;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import utils.PrintUtils;

/**
 * Create by haifei on 12/7/2020 10:54 AM.
 */
public class PathwithMaximumProbability {

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        int row = edges.length;

        Map<Integer, Set<double[]>> map = new HashMap<>();

        for (int i = 0; i < row; i++) {
            int a = edges[i][0], b = edges[i][1];

            if (!map.containsKey(a)) {
                map.put(a, new HashSet<>());
            }
            map.get(a).add(new double[] {b, succProb[i]});

            if (!map.containsKey(b)) {
                map.put(b, new HashSet<>());
            }
            map.get(b).add(new double[] {a, succProb[i]});
        }

        LinkedList<Node> queue = new LinkedList<>();
        Node root = new Node(start, 1.0);
        queue.add(root);

        double[] probs = new double[n];

        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            int cur = poll.start;
            double prob = poll.cost;

            Set<double[]> neighbours = map.getOrDefault(cur, new HashSet<>());
            for (double[] neighbour : neighbours) {
                if (probs[(int) neighbour[0]] >= prob * neighbour[1]) {
                    continue;
                }
                queue.add(new Node((int) neighbour[0], prob * neighbour[1]));
                probs[(int) neighbour[0]] = prob * neighbour[1];
            }
        }

//        PrintUtils.printArray(probs);
        return probs[end];
    }

    public class Node {
        int start;
        double cost;

        public Node(int node, double prob) {
            this.start = node;
            this.cost = prob;
        }

        @Override
        public String toString() {
            return "Node{" + "start=" + start + ", cost=" + cost + '}';
        }
    }

    @Test
    public void test3() {
        int[][] edges = {{0, 3}, {1, 7}, {1, 2}, {0, 9}};
        double[] cost = {0.31, 0.9, 0.86, 0.36};

        double result = maxProbability(10, edges, cost, 2, 3);

        System.out.println(result);

        Assert.assertTrue(0 == result);
    }

    @Test
    public void test() {
        int[][] edges = {{0, 1}, {1, 2}, {0, 2}};
        double[] cost = {0.5, 0.5, 0.2};

        double result = maxProbability(3, edges, cost, 0, 2);

        System.out.println(result);

        Assert.assertTrue(0.25 == result);
    }

    @Test
    public void test1() {
        int[][] edges = {{0, 1}, {1, 2}, {0, 2}};
        double[] cost = {0.5, 0.5, 0.3};

        double result = maxProbability(3, edges, cost, 0, 2);

        System.out.println(result);

        Assert.assertTrue(0.3 == result);
    }
}
