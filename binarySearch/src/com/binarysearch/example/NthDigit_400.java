package com.binarysearch.example;

public class NthDigit_400 {
    public static void main(String[] args) {
        //System.out.println(findNthDigit_2(Integer.MAX_VALUE));
        System.out.println(findNthDigit_2(1000000000));
        //System.out.println(findNthDigit(5500));
    }

    /**
     * Method 2: 找规律
     * | 数字num      | 数位digit | 数量k  | 位数长度cnt |
     * | 1 - 9       | 1        |  9     | 9         |
     * | 10 -99      | 2        |  90    | 180       |
     * | 100 - 999   | 3        |  900   | 2700      |
     * | start-end   | digit    |  k     | cnt       |
     * 递推公式
     * 1. 确定组数i
     * 2. 确定是i这组的第几个数字
     * 3. 确定是num中的哪个数字
     * */
    public static int findNthDigit_2(int n) {
        int digit = 1;
        long start = 1;
        long cnt = 9;

        // 确定组数i, digit数位
        while(n > cnt) {
            n -= cnt;  // 写在最前，防止变负数
            digit += 1;
            start = start * 10;  // 可能会溢出..
            cnt = digit * start * 9;
        }

        //System.out.println("digit="+digit+" start="+start+" n="+n);

        // 确定数组中的第几个数
        long num = start + (n-1) / digit;
        int d = (n-1) % digit; // d从0开始
        return Long.toString(num).charAt(d)-'0';
    }

    /**
     * Method 1: 超时
     * */
    public static int findNthDigit(int n) {
        int pre = 0, cur = 0;
        // 以数字i结尾，有多少长度的数字
        for(int i=1;i<=n;i++) {
            cur = pre + countDigit_binary(i);
            if(cur == n) return i%10;
            else if(cur > n) {
                int remain = n - pre;
                String s = String.valueOf(i);
                return s.charAt(remain-1) - '0';
            }
            pre = cur;
        }
        return pre;
    }

    /**
     * n 除以 (k*10）k取值[1...n]
     * 找到 n/(k*10) == 0的最小值
     * 即 右区间的左端点
     * */
    private static int countDigit_binary(int n) {
        if(n<0) return -1;

        int left = 1, right = n;  // 代表10的个数

        while(left < right) {
            int mid = left + (right - left) / 2;
            if( (int) (n* Math.pow(10, -mid)) == 0) {
                right = mid;
            } else
                left = mid + 1;
        }
        return left;
    }
}
