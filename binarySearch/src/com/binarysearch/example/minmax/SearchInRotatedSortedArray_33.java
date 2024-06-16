package com.binarysearch.example.minmax;

public class SearchInRotatedSortedArray_33 {
    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        System.out.println(search_2(nums, 0));
        System.out.println(search_4(nums, 0));
    }

    /**
     * 方法一: 穷举可能性
     * */
    /*public static int search(int[] nums, int target) {
        int left = 0, right = nums.length-1;

        while(left < right) {
            int mid = left + right + 1>> 1;
            if(nums[mid] == target) return mid;

            if(target >= nums[left]) {
                // target落入左边区间
                if(nums[mid] < nums[left]) {
                    // mid在右区间
                    right = mid - 1;
                } else {
                    // mid在左区间
                    if(nums[mid] > target) right = mid - 1;
                    else left = mid;
                }
            } else {
                // target 落入右边区间
                if(nums[mid] >= nums[left]) {
                    // mid落入左区间
                    left = mid + 1;
                } else {
                    // mid 落入右区间
                    if(nums[mid] > target) right = mid - 1;
                    else left = mid;
                }
            }
        }

        // 防止单调序列
        return left < nums.length && nums[left] == target ? left : -1;
    }    */

    /**
     * 方法二：二分法(使用模板)
     * 二分，将数组分为有序部分和无序部分
     * */
    public static int search_2(int[] nums, int target) {
        int left = 0, right = nums.length-1;

        while(left < right) {
            int mid = left + right +1>> 1;
            //if(nums[mid] == target) return mid; 用模板就不能在这里返回

            if(nums[mid] >= nums[left]) {
                // left ... mid有序
                if(target >= nums[left] && target < nums[mid]) right = mid-1;
                else left = mid;  // 使用了模板

            } else {
                // mid ... right有序
                if(nums[mid] <= target && target <= nums[right]) left = mid;
                else right = mid-1;
            }
        }
        return nums[left] == target ? left: -1;
    }

    /**
     * 二分，不用模板
     * */
    public static int search_3(int[] nums, int target) {
        int left = 0, right = nums.length-1;

        while(left < right) {
            int mid = left + right >> 1;
            if(nums[mid] == target) return mid;

            if(nums[mid] >= nums[left]) {
                // left ... mid有序
                if(target >= nums[left] && target < nums[mid]) right = mid-1;
                else left = mid+1;

            } else {
                // mid ... right有序
                if(nums[mid] < target && target <= nums[right]) left = mid+1;
                else right = mid-1;
            }
        }
        return nums[left] == target ? left: -1;
    }




    /**
     * 20240615
     * [4,5,6,7,0,1,2]
     * 1.关键在于判断mid落入哪个区间，纯有序还是右边的无序
     * 2.每次可以排除一半元素，这是二分法的精髓
     * 3.这一题的元素是unique的
     * */
    public static int search_4(int[] nums, int target) {
        int n = nums.length;

        int left = 0, right = n-1;
        while(left < right) {
            int mid = left+right >> 1;
            if(nums[mid] >= nums[left]) {
                // 落入有序区间
                if(nums[left] <= target && target <= nums[mid]) {
                    // 落入mid的左边
                    right = mid;
                } else
                    left = mid + 1;
            } else {
                // 落入无序区间
                if(target >= nums[mid] && target <= nums[right]) {
                    // 落入mid的右边
                    left = mid;
                } else
                    right = mid-1;
            }
        }
        return nums[left] == target ? left:-1;
    }
}
