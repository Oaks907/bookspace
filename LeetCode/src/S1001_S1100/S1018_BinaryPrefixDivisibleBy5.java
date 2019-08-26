package S1001_S1100;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by haifei on 4/4/2019 4:12 PM.
 * https://leetcode.com/problems/binary-prefix-divisible-by-5/
 */
public class S1018_BinaryPrefixDivisibleBy5 {

  public List<Boolean> prefixesDivBy5(int[] A) {

    List<Boolean> result = new ArrayList<>();

    if (A == null && A.length == 0) {
      return result;
    }

    int curVal = 0;

    for (int i = 0; i < A.length; i++) {
      curVal = ((curVal << 1) + A[i]) % 5;
      System.out.println(curVal);
      result.add(curVal % 5 == 0);
    }

    return result;
  }

  private S1018_BinaryPrefixDivisibleBy5 instance;

  @Before
  public void before() {
    instance = new S1018_BinaryPrefixDivisibleBy5();
  }

  @Test
  public void test4() {

    int[] A = {1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0
      , 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1};

    System.out.println(instance.prefixesDivBy5(A));
  }

  @Test
  public void test3() {
    int[] A = {0, 1, 1, 1, 1, 1};

    System.out.println(instance.prefixesDivBy5(A)); //true,false,false,false,true,false
  }

  @Test
  public void test2() {
    int[] A = {1, 1, 1};

    System.out.println(instance.prefixesDivBy5(A)); //false,false,false
  }

  @Test
  public void test1() {
    int[] A = {0, 1, 1};

    System.out.println(instance.prefixesDivBy5(A)); //true, false, false
  }

}
