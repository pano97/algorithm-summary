# Bitwise Operations
In computer, integer numbers are stored in 32-bit binary format.
## Basic Operations
### Bitwise OR(I)
a|b=7
```aidl
a = 5 = 0101
b = 7 = 0111

Bitwise OR Operation of 5 and 7:
0 1 0 1
0 1 1 1
_______
0 1 1 1 = 7
```

### Bitwise AND(&)
a&b=5
```aidl
a = 5 = 0101
b = 7 = 0111

Bitwise AND Operation of 5 and 7:
0 1 0 1
0 1 1 1
_______
0 1 0 1 = 5
```

### Bitwise XOR(^)
a^b=2
```aidl
a = 5 = 0101
b = 7 = 0111

Bitwise XOR Operation of 5 and 7:
0 1 0 1
0 1 1 1
_______
0 0 1 0 = 2
```
Can consider XOR operation as a sum operation without carrying.
### Bitwise Complement(~)
~a=10
```aidl
a = 5 = 0101

Bitwise Complement Operation of 5:
0 1 0 1
_______
1 0 1 0 = 10
```

### Bit-Shift Operations
- Signed shift operation: >> or <<
- Unsigned shift operation: >>> or <<<

## Tricks
### Determine odd or even number
```aidl
public boolean isOdd(int number) {
    return n&1==0 ? false: true;
}
```
### Swap two numbers
a = a ^ b 

b = (a ^ b) ^ b = a

a = a ^ b = (a ^ b) ^ a = b

```aidl
public void swapNumber(int a, int b) {
    a = a ^ b;
    b = a ^ b;
    a = a ^ b;
}
```
### Get the i-th digit in a binary number
```aidl
public int getIthDigitInBinary(int n, int i) {
    return n & (1 << i);
}
```
### Set the rightmost 1 to 0
n = n & (n-1)
```aidl
a   = 11 = 1 0 1 1
a-1 = 10 = 1 0 1 0
__________________
           1 0 1 0

```
### How many 1-bit in binary number
##### Method 1: use n&(n-1)
- Use the trick **n&(n-1)** to set the rightmost 1 to 0.
- Reset the n.
- Do the above in a loop, break when n is 0.

Since we reset the rightmost 1 to 0 in the number, the number will be decreased gradually.

```aidl
public int countOneBitInBinary(int n) {
    int cnt = 0;
    while(n > 0) {
        n = n & (n-1);
        ++cnt;
    }
    return cnt;
}
```
#### Method 2: Loop 32 position and determine the bit
```aidl
public int countOneBitInBinary(int n) {
        int cnt = 0;
        for(int i=0;i<32;i++) {
            int b = n & (1 << i); // Get the lowest i-th bit of n
            if(b!=0) ++cnt;
        }
        return cnt;
    }

```
### Bitwise operation addition
Add two numbers without addition operation, only use bitwise operation.
```aidl
    public int addTwoNumbers(int num1, int num2) {
        int a = num1 ^ num2; // sum without carry
        int b = num1 & num2; // for carry
        b <<= 1;
        if(b == 0) return a;  // no carry
        return addTwoNumbers(a, b);
    }
```

## Examples
### Leetcode 190. Reverse Bits
Reverse bits of a given 32 bits unsigned integer.

**Example 1:**
```
Input: n = 00000010100101000001111010011100
Output:    964176192 (00111001011110000010100101000000)
```

```aidl
public int reverseBits(int n) {
        int rev = 0;
        for(int i=0;i<32;i++) {
            int b = (n >> i) & 1;  // Get the lowest-ith digit
            rev |= b << (31-i);
        }
        return rev;
}
```

### Leetcode 136. Single Number
One element only appear once, others appear twice.

**Example 1:**
```aidl
Input: nums = [2,2,1]
Output: 1
```
**Example 2:**
```aidl
Input: nums = [4,1,2,1,2]
Output: 4
```
- Set a int oxr with value 0.
- Use the oxr to ^ with all numbers in int[] nums.
```aidl
/**
     * Example 4: Single number.
     * Only one number appear once, others appear twice.
     * Suppose int[] nums = [a, a, b, c, c]
     * int oxr = 0
     * oxr ^ nums = [b]
* */
    public int singleNumber(int[] nums) {
        int oxr = 0;
        for(int n: nums)
            oxr ^= n;
        return oxr;
    }
```
### 137. Single Number II
Only one number appears once, others appear three times.

**Example 1**:
```aidl
Input: nums = [2,2,3,2]
Output: 3
```

**Example 2**:
```aidl
Input: nums = [0,1,0,1,0,1,99]
Output: 99
```

**Analyze**

Suppose the int[] nums = [3, 3, 3, 2, 5, 5, 5]
```aidl
3 = 0 0 1 1
3 = 0 0 1 1
3 = 0 0 1 1
2 = 0 0 1 0
5 = 0 1 0 1
5 = 0 1 0 1
5 = 0 1 0 1
___________
    0 3 4 6   %3
    0 0 1 0
```
Add the sum of digit(i), and do the mod operation.
```aidl
public int singleNumber(int[] nums) {
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
```

### Leetcode 201. Bitwise AND of Numbers Range
Given two integers left and right that represent the range [left, right], return the bitwise AND of all numbers in this range, inclusive.

**Example 1**:
```aidl
Input: left = 5, right = 7
Output: 4
```
     * 5 = 0 1 0 1
     * 6 = 0 1 1 0
     * 7 = 0 1 1 1
     * ------------
     *     0 1 0 0 = 4
     * Return the longest common prefix.
     * How to do it?
     * Set the larger number b's rightest 1 to 0.
     * Loop the operation while a != b
Return the longest common prefix.
```aidl
public int rangeBitwiseAnd(int left, int right) {
        while(right > left) {
            right = right & (right - 1); // set the rightest 1 to 0
        }
        return right;
    }
```