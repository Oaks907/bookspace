package S101_200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Create by haifei on 5/11/2020 11:48 AM.
 */
public class S127_WordLadder {

    /**
     * TLE
     * Time Limit Exceed
     */
    public int ladderLength_TLE(String beginWord, String endWord, List<String> wordList) {
        boolean[] hadUsed = new boolean[wordList.size()];
        recursion(wordList, 1, hadUsed, beginWord, endWord);
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    int result = Integer.MAX_VALUE;
    List<String> list = new ArrayList<>();

    public void recursion(List<String> wordList, int count, boolean[] hadUsed, String curStr, String endWord) {
        if (endWord.equals(curStr)) {
            result = Math.min(result, count);
            //            System.out.println(list);
            return;
        }

        for (int i = 0; i < wordList.size(); i++) {
            if (hadUsed[i]) {
                continue;
            }
            String a = wordList.get(i);
            if (canChange(a, curStr)) {
                hadUsed[i] = true;
                list.add(a);
                recursion(wordList, count + 1, hadUsed, a, endWord);
                hadUsed[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean canChange(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        int diffIndexSize = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diffIndexSize++;
            }
        }
        return diffIndexSize == 1;
    }


    Map<String, Integer> wordId = new HashMap<>();
    List<List<Integer>> edge = new ArrayList<>();
    int nodeNum = 0;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        for (String word : wordList) {
            addEdge(word);
        }
        addEdge(beginWord);

        if (!wordId.containsKey(endWord)) {
            return 0;
        }

        int[] dis = new int[nodeNum];
        Arrays.fill(dis, Integer.MAX_VALUE);
        int beginId = wordId.get(beginWord), endId = wordId.get(endWord);
        dis[beginId] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(wordId.get(beginWord));

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            if (poll == endId) {
                return dis[endId] / 2 + 1;
            }

            for (Integer it : edge.get(poll)) {
                if (dis[it] == Integer.MAX_VALUE) {
                    dis[it] = dis[poll] + 1;
                    queue.offer(it);
                }
            }
        }

        return 0;
    }

    private void addEdge(String word) {
        addWord(word);
        char[] array = word.toCharArray();
        Integer id1 = wordId.get(word);

        for (int i = 0; i < array.length; i++) {
            char tmp = array[i];
            array[i] = '*';
            String newWord = new String(array);
            addWord(newWord);
            Integer id2 = wordId.get(newWord);
            edge.get(id1).add(id2);
            edge.get(id2).add(id1);
            array[i] = tmp;
        }
    }

    private void addWord(String word) {
        if (!wordId.containsKey(word)) {
            wordId.put(word, nodeNum++);
            edge.add(new ArrayList<>());
        }
    }



    @Test
    public void test() {
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        int result = ladderLength("hit", "cog", wordList);
        Assert.assertEquals(5, result);
    }

    @Test
    public void test1() {
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log");
        int result = ladderLength("hit", "cog", wordList);
        Assert.assertEquals(0, result);
    }

}
