package com.lc.tree.bit;

public class BitwiseTemplate {
    public static void main(String[] args) {
        System.out.println("========================Case 1: Count 1 bit========================");
        System.out.println(countOneBitInBinary_2(7)); // 3
        System.out.println(countOneBitInBinary_2(15));
        System.out.println("========================Case 2: Add 2 numbers=======================");
        System.out.println(addTwoNumbers(-3,-5));


    }
    /**
     * Example 1: How many 1-bit in a binary number.
     * */
    public static int countOneBitInBinary(int n) {
        int cnt = 0;
        while(n > 0) {
            n = n & (n-1);
            ++cnt;
        }
        return cnt;
    }
    public static int countOneBitInBinary_2(int n) {
        int cnt = 0;
        for(int i=0;i<32;i++) {
            int b = n & (1 << i); // Get the lowest i-th bit of n
            if(b!=0) ++cnt;
        }
        return cnt;
    }

    /**
     * Example 2: Add two numbers
     * */
    public static int addTwoNumbers(int num1, int num2) {
        int a = num1 ^ num2; // sum without carry
        int b = num1 & num2; // for carry
        b <<= 1;
        if(b == 0) return a;  // no carry
        return addTwoNumbers(a, b);
    }

    /**
     * Example 3: Reverse Bits
     * */
    public static int reverseBits_190(int n) {
        int rev = 0;
        for(int i=0;i<32;i++) {
            int b = (n >> i) & 1;
            rev |= b << (31-i);
        }
        return rev;
    }

    /**
     * Example 4: Single number.
     * Only one number appear once, others appear twice.
     * Suppose int[] nums = [a, a, b, c, c]
     * int oxr = 0
     * oxr ^ nums = [b]
     * */
    public static int singleNumber_136(int[] nums) {
        int oxr = 0;
        for(int n: nums)
            oxr ^= n;
        return oxr;
    }

    /**
     * Example 5: Single number
     * */
    public static int singleNumber_137(int[] nums) {
        int ans = 0;
        for(int i=0;i<32;i++) {
            int sum = 0;
            for(int n: nums) {
                sum += (n >> i) & 1;
            }
            ans |= (sum % 3) << i;
        }
        return ans;
    }

    /**
     * Example 6: Bitwise AND of Numbers Range
     * 5 = 0 1 0 1
     * 6 = 0 1 1 0
     * 7 = 0 1 1 1
     * ------------
     *     0 1 0 0 = 4
     * Return the longest common prefix.
     * How to do it?
     * Set the larger number b's rightest 1 to 0.
     * Loop the operation while a != b
     * */
    public static int rangeBitwiseAnd_201(int left, int right) {
        while(right > left) {
            right = right & (right - 1); // set the rightest 1 to 0
        }
        return right;
    }

}
