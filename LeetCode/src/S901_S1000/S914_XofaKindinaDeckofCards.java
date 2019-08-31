package S901_S1000;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Create by haifei on 26/3/2019 6:47 PM.
 * <p>
 * https://leetcode.com/problems/x-of-a-kind-in-a-deck-of-cards/
 */
public class S914_XofaKindinaDeckofCards {

  public boolean hasGroupsSizeX(int[] deck) {

    if (deck == null || deck.length == 0) {
      return false;
    }

    Arrays.sort(deck);

    int comboNumber = Integer.MIN_VALUE;
    int combo = 0;
    int lastCombo = 0;

    for (int num : deck) {

      //刚进入循环时，combo为 0
      if (combo == 0) {
        comboNumber = num;
        combo++;
      } else {

        if (comboNumber == num) {
          combo++;
        } else {
          comboNumber = num;

          if (lastCombo == 0) {
            lastCombo = combo;
          } else {
            if (gcd(combo, lastCombo) < 2) {
              return false;
            }

            lastCombo = Math.max(combo, lastCombo);
          }

          combo = 1;
        }
      }
    }

    if (lastCombo != 0 && gcd(lastCombo, combo) < 2) {
      return false;
    }

    return combo >= 2;
  }

  private int gcd(int num1, int num2) {
    while (num1 != num2) {
      if (num1 > num2) {
        num1 = num1 - num2;
      } else {
        num2 = num2 - num1;
      }
    }
    return num1;
  }


  S914_XofaKindinaDeckofCards solution;

  @Before
  public void before() {
    solution = new S914_XofaKindinaDeckofCards();
  }

  @Test
  public void testCase7() {
    int[] nums = {1, 1, 1, 1, 2, 2, 2, 2, 2, 2};
    Assert.assertTrue(solution.hasGroupsSizeX(nums));
  }

  @Test
  public void testCase6() {
    int[] nums = {1, 1, 1, 2, 2, 2, 3, 3};
    Assert.assertFalse(solution.hasGroupsSizeX(nums));
  }

  @Test
  public void testCase5() {
    int[] nums = {1, 1, 2, 2, 2, 2};
    Assert.assertTrue(solution.hasGroupsSizeX(nums));
  }

  @Test
  public void testCase4() {
    int[] nums = {1, 1};
    Assert.assertTrue(solution.hasGroupsSizeX(nums));
  }

  @Test
  public void testCase3() {
    int[] nums = {1};
    Assert.assertFalse(solution.hasGroupsSizeX(nums));
  }

  @Test
  public void testCase2() {
    int[] nums = {1, 1, 1, 2, 2, 2, 3, 3};
    Assert.assertFalse(solution.hasGroupsSizeX(nums));
  }

  @Test
  public void testCase1() {
    int[] nums = {1, 2, 3, 4, 4, 3, 2, 1};
    Assert.assertTrue(solution.hasGroupsSizeX(nums));
  }
}
