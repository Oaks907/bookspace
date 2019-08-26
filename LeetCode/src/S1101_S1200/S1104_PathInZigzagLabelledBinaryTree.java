package S1101_S1200;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/path-in-zigzag-labelled-binary-tree/
 * Create by haifei on 25/8/2019 7:28 PM.
 */
public class S1104_PathInZigzagLabelledBinaryTree {
  public List<Integer> pathInZigZagTree(int label) {

    List<Integer> result = new ArrayList<>();

    int level = getLevel(label);
    while (level > 0) {
      result.add(label);
      int gap;
      if (isOddNumber(level)) {
        int left = (int) (Math.pow(2, level) - 1);
        int lefter = (int) Math.pow(2, level - 2);
        gap = (left - label) / 2;
        label = lefter + gap;
      } else {
        int left = (int) Math.pow(2, level - 1);
        int lefter = (int) (Math.pow(2, level - 1) - 1);
        gap = (label - left) / 2;
        label = lefter - gap;
      }
      level--;
    }
    Collections.reverse(result);
    return result;
  }

  /**
   * 根据数字获取它位于书的第几层
   *
   * @return
   */
  public int getLevel(int number) {
    int level = 0;
    while (Math.pow(2, level) <= number) {
      level++;
    }
    return level;
  }

  /**
   * 判断奇偶数
   *
   * @param number
   * @return
   */
  public boolean isOddNumber(int number) {
    return number % 2 == 0;
  }

  public static void main(String[] args) {
    final S1104_PathInZigzagLabelledBinaryTree path =
      new S1104_PathInZigzagLabelledBinaryTree();
    System.out.println(path.getLevel(8));

    System.out.println(path.pathInZigZagTree(26));
  }
}
