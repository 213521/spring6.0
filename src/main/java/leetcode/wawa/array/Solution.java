package main.leetcode.wawa.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 功能描述:
 *
 * @author sliu
 * @date 2021/3/18 4:15 下午
 */
public class Solution {


    public int lastRemaining(int n, int m) {



        return 0;
    }

//
//    public static void mergeArr(int[] A, int m, int[] B, int n) {
//        if (A.length < B.length == 0) return;
//        int ai = m - 1;
//        int bi = n - 1;
//        for (int i = m + n - 1; i >= 0; i--) {
//            if (ai >= 0 && bi >= 0) {
//                if (A[m - 1] >= B[n - 1]) {
//                    A[i] = A[ai];
//                    ai--;
//                } else {
//                    A[i] = B[bi];
//                    bi--;
//                }
//            } else {
//                if (m >= 1) {
//                    A[i] = A[ai];
//                    ai--;
//                }
//                if (n >= 1) {
//                    A[i] = B[bi];
//                    bi--;
//                }
//            }
//        }
//    }


    public static void mergeSort(int[] nums, int low, int high) {
        int mid = (low + high) / 2;
        mergeSort(nums, low, mid);
        mergeSort(nums, mid + 1, high);
        merge(nums, low, mid, high);
    }


    public static void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }


    public static void merge(int[] a, int left, int mid, int right) {
        // 辅助数组
        int[] tmp = new int[a.length];
        //p1、p2是检测指针，k是存放指针
        int p1 = left, p2 = mid + 1, k = left;
        while (p1 <= mid && p2 <= right) {
            if (a[p1] <= a[p2])
                tmp[k++] = a[p1++];
            else
                tmp[k++] = a[p2++];
        }
        // 如果第一个序列未检测完，直接将后面所有元素加到合并的序列中
        while (p1 <= mid) tmp[k++] = a[p1++];
        while (p2 <= right) tmp[k++] = a[p2++];

        // 复制回原素组
        for (int i = left; i <= right; i++)
            a[i] = tmp[i];
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 6, 3, 9, 5, 2, 7};
//        quickSort(nums, 0, nums.length - 1);
//        mergeSort(nums, 0, nums.length - 1);
        bubbleSort(nums);
        printArray(nums);


    }

    public static void quickSort(int[] nums, int low, int high) {
        if (nums == null || nums.length == 0) return;
        if (low < high) {
            int p = part(nums, low, high);
            quickSort(nums, low, p - 1);
            quickSort(nums, p + 1, high);
        }

    }

    public static int part(int[] nums, int low, int high) {
        int h = high;
        int last = nums[high];
        while (low < high) {
            while (low < high && nums[low] <= last) {
                low++;
            }
            if (low < high) {
                swap(nums, --high, low);
            }
        }
        swap(nums, h, low);
        return low;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }


    public static void printArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print("\t" + nums[i]);
        }
        System.out.println();
    }


    /**
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
     */
    public static int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return new int[0];
        int rows = matrix.length;
        int columns = matrix[0].length;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;

        int index = 0;
        int[] ans = new int[rows * columns];

        while ((left <= right && top <= bottom)) {
            for (int column = left; column <= right; column++) {
                ans[index++] = matrix[top][column];
            }
            System.out.println(Arrays.toString(ans));
            for (int row = top + 1; row <= bottom; row++) {
                ans[index++] = matrix[row][right];
            }

            for (int column = right - 1; column >= left; column--) {
                ans[index++] = matrix[bottom][column];
            }
            for (int row = bottom - 1; row > top; row--) {
                ans[index++] = matrix[row][left];
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return ans;
    }


    /**
     * 12345666678
     * 统计一个数字在排序数组中出现的次数。
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0, right = nums.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (right - left) / 2 + left;
            if (nums[mid] == target) break;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(mid);
        int counter = 0;
        left = mid;
        right = mid + 1;
        while (nums[left--] == target && left >= 0) {
            counter++;
        }
        while (nums[right++] == target && right < nums.length) {
            counter++;
        }
        return counter;
    }


    /**
     * 找出数组中重复的数字。
     * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
     * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
     */
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) {
                return nums[i];
            }
            set.add(nums[i]);
        }
        return -1;
//        for (int i = 0; i < nums.length; i++) {
//            while (nums[i] != i) {
//                return nums[i];
//            }
//            int temp = nums[i];
//            nums[i] = nums[temp];
//            nums[temp] = temp;
//        }
//        return -1;

    }
}
