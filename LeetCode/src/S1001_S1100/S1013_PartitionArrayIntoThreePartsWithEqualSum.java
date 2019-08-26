package S1001_S1100;

import org.junit.Test;

/**
 * Create by haifei on 24/3/2019 10:40 AM.
 */
public class S1013_PartitionArrayIntoThreePartsWithEqualSum {

  public boolean canThreePartsEqualSum(int[] A) {

    if (null == A || A.length == 0) {
      return false;
    }

    int sum = 0;

    for (int a : A) {
      sum += a;
    }

    if (sum % 3 != 0) {
      return false;
    }

    int partition = 0;

    for (int a : A) {
      partition = partition + a == sum / 3 ? 0 : partition + a;
    }

    return partition == 0;
  }

  @Test
  public void test() {

    int[] A = {3, 3, 6, 5, -2, 2, 5, 1, -9, 4};

    final S1013_PartitionArrayIntoThreePartsWithEqualSum withEqualSum =
      new S1013_PartitionArrayIntoThreePartsWithEqualSum();
//    System.out.println(withEqualSum.canThreePartsEqualSum(A));

    A = new int[]{18, 12, -18, 18, -19, -1, 10, 10};

    System.out.println(withEqualSum.canThreePartsEqualSum(A));
  }
}
