package S201_300;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by haifei on 19/1/2019 3:39 PM.
 * <p>
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 */
public class S208_ImplementPrefixTrie {


  TrieNode root;

  /**
   * Initialize your data structure here.
   */
  public S208_ImplementPrefixTrie() {
    root = new TrieNode();
  }

  /**
   * Inserts a word into the trie.
   */
  public void insert(String word) {

    final char[] chars = word.toCharArray();
    TrieNode head = root;

    for (int i = 0; i < chars.length; i++) {

      head.map.putIfAbsent(chars[i], new TrieNode(chars[i]));
      final TrieNode trieNode = head.map.get(chars[i]);

      head = trieNode;
    }

    head.isWord = true;
  }

  /**
   * Returns if the word is in the trie.
   */
  public boolean search(String word) {

    final char[] chars = word.toCharArray();

    TrieNode head = root;

    for (int i = 0; i < chars.length; i++) {

      final TrieNode trieNode = head.map.get(chars[i]);

      if (trieNode == null) {
        return false;
      } else {
        head = trieNode;
      }
    }

    return head.isWord;
  }

  /**
   * Returns if there is any word in the trie that starts with the given prefix.
   */
  public boolean startsWith(String prefix) {
    final char[] chars = prefix.toCharArray();

    TrieNode head = root;

    for (int i = 0; i < chars.length; i++) {

      final TrieNode trieNode = head.map.get(chars[i]);

      if (trieNode == null) {
        return false;
      } else {
        head = trieNode;
      }
    }

    return true;
  }

  class TrieNode {
    char charStr;
    Map<Character, TrieNode> map = new HashMap();
    boolean isWord = false;

    public TrieNode(){

    }

    public TrieNode(char charStr){
      this.charStr = charStr;
    }
  }

  public static void main(String[] args) {
    S208_ImplementPrefixTrie trie = new S208_ImplementPrefixTrie();

    trie.insert("apple");
    System.out.println(trie.search("apple"));   // returns true
    System.out.println(trie.search("app"));     // returns false
    System.out.println(trie.startsWith("app")); // returns true
    trie.insert("app");
    System.out.println(trie.search("app"));     // returns true
  }
}
