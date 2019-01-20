package S901_S1000;

/**
 * Create by haifei on 20/1/2019 11:15 AM.
 */
public class S978_LongestTurbulentSubarray {

  public int maxTurbulenceSize(int[] A) {

    if (A == null || A.length == 0) {
      return 0;
    }

    if (A.length == 1) {
      return 1;
    }

    int maxTurbulence = 1;
    int turbulence = 1;
    Boolean preIsLower = null;

    for (int i = 1; i < A.length; i++) {
      if (null == preIsLower) {
        preIsLower = A[i - 1] < A[i];   // A[0] < a[1]
        turbulence++;
        maxTurbulence = Math.max(maxTurbulence, turbulence);
        continue;
      }


      if (preIsLower) {
        preIsLower = A[i - 1] < A[i];
        //  <  >
        if (A[i - 1] > A[i]) {
          turbulence++;
        } else {
          turbulence = 2;
        }
      } else {
        preIsLower = A[i - 1] < A[i];
        //  <  >
        if (A[i - 1] < A[i]) {
          turbulence++;
        } else {
          turbulence = 2;
        }
      }
      maxTurbulence = Math.max(maxTurbulence, turbulence);
    }

    return maxTurbulence;
  }

  public static void main(String[] args) {
    final S978_LongestTurbulentSubarray turbulentSubarray =
      new S978_LongestTurbulentSubarray();

    int[] arr = {9, 4, 2, 10, 7, 8, 8, 1, 9};
//    System.out.println(turbulentSubarray.maxTurbulenceSize(arr));
//
//    arr = new int[]{4, 8, 12, 16};
//    System.out.println(turbulentSubarray.maxTurbulenceSize(arr));
//
//    arr = new int[]{100};
//    System.out.println(turbulentSubarray.maxTurbulenceSize(arr));

    arr = new int[]{0, 8, 45, 88, 48, 68, 28, 55, 17, 24};
    System.out.println(turbulentSubarray.maxTurbulenceSize(arr));
  }
}
