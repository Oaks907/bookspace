package S201_300;

import sun.tools.jconsole.Worker;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by haifei on 20/1/2019 9:16 PM.
 * <p>
 * https://leetcode.com/problems/word-search-ii/
 */
public class S212_WordSearchII {

  public List<String> findWords(char[][] board, String[] words) {

    List<String> result = new ArrayList<>();

    if (board == null || words == null) {
      return result;
    }

    TrieNode root = buildTrie(words);

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        dfs(board, i, j, root, result);
      }
    }

    return result;
  }

  private void dfs(char[][] board, int i, int j, TrieNode p, List<String> list) {

    int index = board[i][j] - 'a';
    char c = board[i][j];

    //已标记或者字典树中不存在
    if (board[i][j] == '#' || p.next[index] == null) {
      return;
    }

    p = p.next[c - 'a'];

    if (p.word != null) {
      list.add(p.word);
      //防止重复搜索
      p.word = null;
    }

    //访问过的就标记
    board[i][j] = '#';

    //搜索左边
    if (i > 0) {
      dfs(board, i - 1, j, p, list);
    }
    if (j > 0) {
      dfs(board, i, j - 1, p, list);
    }
    if (i < board.length - 1) {
      dfs(board, i + 1, j, p, list);
    }
    if (j < board[0].length - 1) {
      dfs(board, i, j + 1, p, list);
    }

    //重置状态位
    board[i][j] = c;
  }

  public TrieNode buildTrie(String[] words) {
    TrieNode root = new TrieNode();

    for (String str : words) {

      TrieNode head = root;

      final char[] chars = str.toCharArray();

      for (int i = 0; i < chars.length; i++) {

        int index = chars[i] - 'a';

        if (head.next[index] == null) {
          head.next[index] = new TrieNode();
        }
        head = head.next[index];
      }

      //最后一个节点存储word
      head.word = str;
    }

    return root;
  }

  class TrieNode {
    TrieNode[] next = new TrieNode[26];
    String word;
  }

  public static void main(String[] args) {

    final S212_WordSearchII wordSearchII = new S212_WordSearchII();

    char[][] chars = {
      {'o','a','a','n'},
      {'e','t','a','e'},
      {'i','h','k','r'},
      {'i','f','l','v'}
    };

    String[] words = {"oath","pea","eat","rain"};

    System.out.println(wordSearchII.findWords(chars, words));
  }
}
