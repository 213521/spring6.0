package main.leetcode.wawa.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 功能描述:
 *
 * @author sliu
 * @date 2021/4/10 11:20 下午
 */
public class Daily {
    public static void main(String[] args) {
        List<List<Integer>> subsets = subsets(new int[]{1, 2, 3});
        System.out.println(subsets);
    }

    public static List<List<Integer>> subsets(int[] nums) {
//        List<List<Integer>> res = new ArrayList<>();
//        backtrack(0, nums, res, new ArrayList<Integer>());
//        return res;
        List<List<Integer>> result = new ArrayList<>();
        // 先加一个空子集
        result.add(new ArrayList<>());

        for (int num : nums) {
            // 下一个需要添加的子集，把原来的子集复制一遍，在后边追加下一个num
            List<List<Integer>> nextSub = new ArrayList<>();

            for (List<Integer> tempList : result) {
                // 复制原来的每一个
                List<Integer> newSub = new ArrayList<>(tempList);
                // 添加新的num
                newSub.add(num);
                nextSub.add(newSub);
            }
            result.addAll(nextSub);

        }
        return result;


    }

    private void backtrack(int i, int[] nums, List<List<Integer>> res, ArrayList<Integer> tmp) {
        res.add(new ArrayList<>(tmp));
        for (int j = i; j < nums.length; j++) {
            tmp.add(nums[j]);
            backtrack(j + 1, nums, res, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }


    public static String largestNumber(int[] nums) {
        int n = nums.length;
        String[] numsToWord = new String[n];
        for (int i = 0; i < n; i++) {
            numsToWord[i] = String.valueOf(nums[i]);
        }
        //compareTo()方法比较的时候是按照ASCII码逐位比较的
        //通过比较(a+b)和(b+a)的大小，就可以判断出a,b两个字符串谁应该在前面
        //所以[3,30,34]排序后变为[34,3,30]
        //[233，23333]排序后变为[23333，233]
        Arrays.sort(numsToWord, (a, b) -> (b + a).compareTo(a + b));
        //如果排序后的第一个元素是0，那后面的元素肯定小于或等于0，则可直接返回0
        if (numsToWord[0].equals("0")) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(numsToWord[i]);
        }
        return sb.toString();

    }

    private static void setEveryNumber(int number, List<Integer> list) {
        while (number >= 10) {
            list.add(number % 10);
            number /= 10;
        }
        list.add(number % 10);
    }


    public int nthUglyNumber2(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int p1 = 1, p2 = 1, p3 = 1;
        for (int i = 2; i <= n; i++) {
            int n1 = dp[p1] * 2;
            int n2 = dp[p2] * 3;
            int n3 = dp[p3] * 5;
            dp[i] = Math.min(Math.min(n1, n2), n3);
            if (dp[i] == n1) {
                p1++;
            }
            if (dp[i] == n2) {
                p2++;
            }
            if (dp[i] == n3) {
                p3++;
            }
        }
        return dp[n];
    }

    /**
     * 第n个丑数
     *
     * @param n
     *
     * @return
     */
    public int nthUglyNumber(int n) {
        int count = 1;
        int ans = 0;
        int x = 1;
        while (count <= n) {
            if (isUgly(x)) {
                count++;
                ans = x;
            }
            x++;
        }
        return ans;
    }

    /**
     * 判断一个数是不是丑数
     *
     * @param n
     *
     * @return
     */
    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        while (n % 2 == 0) {
            n /= 2;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        while (n % 5 == 0) {
            n /= 5;
        }
        return n == 1;
    }


}
