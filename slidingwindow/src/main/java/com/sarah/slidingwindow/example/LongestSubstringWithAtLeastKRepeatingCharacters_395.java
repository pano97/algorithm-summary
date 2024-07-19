package com.sarah.slidingwindow.example;

import java.util.Map;
import java.util.TreeMap;

public class LongestSubstringWithAtLeastKRepeatingCharacters_395 {
    public static void main(String[] args) {
        String s = "a";
        System.out.println(longestSubstring_2(s, 1));
        System.out.println(longestSubstring_3(s,1));
    }

    /**
     * 滑动窗口
     * 1. 遍历s，统计每个字符出现的次数，维护在int[] times数组中
     * 2. 如果k=3,那么总共只出现<3次的字符是不能出现在字串中的
     * 3. 设置双指针 i,j=0
     * 4. 如果j指向的位置的字符，times[s.charAt(j)-'a'] < k, 说明字串不能包含j位置，
     * i=j+1, j=i 窗口从下一个位置开始
     * 5. 如果j指向的位置可以包含在字串中，放入visit treemap <Character, Integer> 统计访问次数,
     * 如果最小的访问次数>=k, 更新长度
     *
     * 注意:
     * s的长度[1,10^4]
     * s只包含消协英文字母
     * k的长度[1,10^5]
     * */
    // s="aaabb", k=3
    public static int longestSubstring(String s, int k) {
        int n = s.length();
        int[] times = new int[26];
        int res = 0;

        // 想要获取当前窗口中各个字母出现的次数
        TreeMap<Character, Integer> visit = new TreeMap<>();

        // init times array, count every character appear time
        for(int i=0;i<n;i++) {
            times[s.charAt(i) - 'a']++;
        }

        int i=0,j=0;
        while(j<n) {
            char ch = s.charAt(j);

            if(times[ch-'a'] < k) {
                // 该字符不该出现在字串中
                i = j+1;
                j = i;
                visit = new TreeMap<>(); // 滑动窗口更新，visit重置
            } else {
                // 该字符可以出现在字串中，更新它的出现次数
                visit.put(ch, visit.getOrDefault(ch, 0)+1);

                int min = findMinInMap(visit);

                // 获取当前窗口中出现次数最少的记录
                if(min >= k) {
                    // 满足
                    res = Math.max(res, j-i+1);
                }
                j++;
            }
        }

        return res;
    }

    private static int findMinInMap(Map<Character, Integer> map) {
        int min = Integer.MAX_VALUE;

        for(Map.Entry<Character, Integer> entry: map.entrySet()) {
            min = Math.min(min, entry.getValue());
        }
        return min;
    }


    /**
     * 方法二：递归 72.54%
     * 如果一个字符在字符串中总的出现次数不超过k，那么这个字符一定不能出现在字串中
     * */
    public static int longestSubstring_2(String s, int k) {
        return dfs(s, k);
    }

    // 返回s中每个字符至少出现k次的最短字串
    private static int dfs(String s, int k) {
        // 设置times数组，统计每个字符出现的次数
        // 这里的times不能是全局数组，因为递归要划分为多个短的string, 应该统计每个string各自出现的字母频次
        int[] times = new int[26];

        // 统计字符串中各个字母出现的次数
        for(int i=0;i<s.length();i++) {
            times[s.charAt(i)-'a']++;
        }

        // 设置分割点
        String point = "";
        for(int i=0;i<times.length;i++) {
            // 出现次数>0 但<k
            int count = times[i];
            if(count > 0 && count < k) {
                char ch = (char) (i +'a');
                point = String.valueOf(ch);
                // 只需要找到一个分割点
                break;
            }
        }

        // 所有的字符都符合要求，返回字串长度
        if("".equals(point)) return s.length();

        // 根据分割点将原字符串分割成若干个字串，分别计算各个字串的最长字串，返回最长的结果
        String[] strs = s.split(point);

        int res = 0;
        for(int i=0;i<strs.length;i++)
        {
            int tmp = dfs(strs[i], k);
            res = Math.max(tmp, res);
        }
        return res;
    }


    /**
     * 方法三：枚举+滑动窗口 72.54%
     * 比较特殊的是这题考虑固定窗口中的元素种类，窗口中的元素种类范围[1,26]
     *
     * */
    public static int longestSubstring_3(String s, int k) {
        int res = 0;
        for(int i=1;i<=26;i++) {
            res = Math.max(findMaxSubLengthWithKind(s, k, i), res);
        }
        return res;
    }

    /**
     * 在字符串s中，连续字串中包含kind种字符，且每个字符出现的频率>=k的最长字串长度
     * */
    private static int findMaxSubLengthWithKind(String s, int k, int kind) {

        int[] count = new int[26]; // 字符词频统计表

        int curKind = 0; // 滑动窗口中当前具有的字符种类
        int curReachKind = 0;  // 已经达标的字符种类

        int res = 0;

        // 固定left指针，从left每个位置开始，计算最长的字串
        for(int left=0,right=0; left<s.length(); left++) {
            // 右指针扩大的情况分析
            // 如果新加入的元素会导致curKind超过kind，说明不能走入while循环，right指针不能右移动
            // 只有没有出现过的元素才可能导致当前种类的增加
            while(right < s.length() && !(count[s.charAt(right)-'a'] == 0 && curKind == kind)) {
                char ch = s.charAt(right);
                count[ch-'a']++; // 更新词频统计

                if(count[ch-'a'] == 1) curKind++;  // 新元素
                if(count[ch-'a'] == k) curReachKind++;  // 这里不能写成else if, 因为k有可能等于1，这是个坑
                ++right;
                // 更新长度不能放在while循环里，因为while循环里对应的并不全是达标的情况
                //res = Math.max(res, right-left);
            }

            if(curReachKind == kind) res = Math.max(res, right-left);

            // 右指针到达了末尾
            if(right == s.length()) break;

            // 左指针缩小
            int index = s.charAt(left)-'a';
            if(count[index] == 1) --curKind;
            if(count[index] == k) --curReachKind;
            --count[index];
        }
        return res;
    }

}
