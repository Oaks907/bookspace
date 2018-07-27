package com.haifei;

/**
 * Create by haifei on 16/7/2018.
 */
public class S08_MinNumberInRotatedArray {

  public int findMin(int[] nums) {
    if (null == nums) {
      throw new IllegalArgumentException();
    }

    int leftIndex = 0, rightIndex = nums.length - 1;
    int middleIndex = leftIndex;

    while (nums[leftIndex] >= nums[rightIndex]) {
      middleIndex = (leftIndex + rightIndex) / 2;
      int leftValue = nums[leftIndex];
      int middleValue = nums[middleIndex];
      int rightValue = nums[rightIndex];

      if (rightIndex - 1 == leftIndex) {
        middleIndex = rightIndex;
        break;
      }

      if (leftValue == rightValue && leftValue == middleValue) {
        return minInOrder(nums, leftIndex, rightIndex);
      }

      if (rightValue > middleValue) {
        rightIndex = middleIndex;
      } else if (rightValue < middleValue) {
        leftIndex = middleIndex;
      }
    }

    return nums[middleIndex];
  }

  private int minInOrder(int[] nums, int leftIndex, int rightIndex) {
    int result = nums[leftIndex];
    for (int i = leftIndex + 1; i < rightIndex; i++) {
      if (nums[i] < result) {
        result = nums[i];
      }
    }
    return result;
  }


  public static void main(String[] args) {
    S08_MinNumberInRotatedArray minNumberInRotatedArray = new S08_MinNumberInRotatedArray();
    int num1[] = {3, 4, 5, 1, 2};
    System.out.println(minNumberInRotatedArray.findMin(num1));
    int num2[] = {1, 1, 1, 0, 1};
    System.out.println(minNumberInRotatedArray.findMin(num2));
  }
}
