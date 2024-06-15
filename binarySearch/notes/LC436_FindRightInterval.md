# LC 436 Find Right Interval
## 问题描述
You are given an array of intervals, where intervals[i] = [starti, endi] and each starti is unique.

The right interval for an interval i is an interval j such that startj >= endi and startj is minimized. Note that i may equal j.

Return an array of right interval indices for each interval i. If no right interval exists for interval i, then put -1 at index i.

### Input and Output
```text
Example 1:
Input: intervals = [[1,2]]
Output: [-1]
Explanation: There is only one interval in the collection, so it outputs -1.

Example 2:
Input: intervals = [[3,4],[2,3],[1,2]]
Output: [-1,0,1]
Explanation: There is no right interval for [3,4].
The right interval for [2,3] is [3,4] since start0 = 3 is the smallest start that is >= end1 = 3.
The right interval for [1,2] is [2,3] since start1 = 2 is the smallest start that is >= end2 = 2.

Example 3:
Input: intervals = [[1,4],[2,3],[3,4]]
Output: [-1,2,-1]
Explanation: There is no right interval for [1,4] and [3,4].
The right interval for [2,3] is [3,4] since start2 = 3 is the smallest start that is >= end1 = 3.
```

## 分析
- intervals是若干组区间，[start_i,end_i] start_i <= end_i
- 各组的start_i是不同的
- input没说有序，需要自己排列
- intervals长度[1,2*10^4]
- start和end的范围[-10^6, 10^6]
- 返回的是index下标，但由于排序了，需要记录原始的start的位置

## 题解
```java
/**
* 方法一：二分法 O(nlogn) 85%
* 由于start_i是唯一的，可以用HashMap记录 (start, position)
* 然后将intervals按照start升序排序
* 对intervals二分，intervals[mid][0] >= start_i
* ps:
* 由于是对排序过后的数组进行遍历，改变了相对位置
* 填充res[i]的时候，要去map中查询原来的位置进行填充
* */
public static int[] findRightInterval(int[][] intervals) {
        int n = intervals.length; // intervals长度不会为0

        // init map, record original index
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<n;i++)
            map.put(intervals[i][0], i);

        // sort interval
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 根据start升序排序
                return o1[0] - o2[0];
            }
        });

        int[] res = new int[n];

        // binary search
       for(int i=0;i<n;i++) {
           // 对每个interval都要查找right interval
           int target = intervals[i][1];

           int left = 0, right = n-1;
           while(left < right) {
               int mid = left + right >> 1;
               if(intervals[mid][0] >= target) {
                   // 右边区间的左端点
                   right = mid;
               } else
                   left = mid + 1;
           }

           int pos_i = map.get(intervals[i][0]);
           int pos_left = map.get(intervals[left][0]);
           res[pos_i] = intervals[left][0] >= target ? pos_left : -1;
       }
        return res;
    }
```
- 时间复杂度O(nlogn)
- 空间复杂度O(n)


## 拓展
**改进**：
用map存储的原始位置，比较重，可以该用另一个二维数组存储。int[][] pos

```java
// TODO: 双指针方法
```