package com.algorithm.demo;

import java.util.Arrays;
import java.util.Comparator;

public class TestAlgorithmPart {

    public static void main(String[] args) {
        
      TestAlgorithmPart test = new TestAlgorithmPart();
      int[] array = {4,1,3,6, 2, 5};
      
      //2.递归计算数据
      quickSort(array, 0, array.length - 1);
      print(array);
      
      //2.递归计算数据
      int sum = test.getSumByRecursion(array,0,array.length - 1);
      System.out.println("递归计算数组大小 :" + sum);
      
      //3.合并两个数组并交叉排列,并输出
      int[] args1 = {0,2,4,6};
      int[] args2 = {1,3};
      print(test.mergeArray(args1, args2));
      
      //4.获取前10（0~9）个菲利波数字
        int i = 9;
        while (i >= 0) {
            System.out.print(test.getRecursively(i)+" ");
            i--;
        }
        System.out.println();
       //5.根据整形数组中的数字组合成一个最大的数字
        int[] max = { 50, 2, 1, 9 };
        System.out.println("maxNum: " + test.makeMaxNum(max));
        
    }
    
    /**
     * 将数组中的数字组装成一个最大的整数并返回
     **/
    public int makeMaxNum(int[] args){
        Integer[] nums = new Integer[args.length];
        for (int i = 0; i < args.length; i++) {
            nums[i] = args[i];
        }
        Arrays.sort(nums, new Comparator<Integer>() {

            @Override
            public int compare(Integer lhs, Integer rhs) {
                String v1 = lhs.toString();
                String v2 = rhs.toString();
                return -(v1 + v2).compareTo(v2 + v1);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (Integer num:nums) {
            sb.append(num);
        }
        return Integer.valueOf(sb.toString());
    }

    /**
     * 获取第n个斐波列数
     **/
    public int getRecursively(int n) {
        if (n > 1) {
            int f1 = 0;
            int f2 = 1;
            int tmp = 0;
            while (--n > 0) {
                tmp = f2;
                f2 += f1;   //运算后f2实际代表新的f3
                f1 = tmp;   //运算后f1实际代表之前f2，这样交换往后移动一下。
            } ;
            return f2;
        }
        return n;
    }
//    public int getRecursively(int n) {
//        if (n > 1) {
//            int f1 = 1;
//            int f2 = 1;
//            n--;  //代表f和 f2已经循环了一次
//            f1 = n & 1; //
//            n /= 2;  //每次循环计算两次
//            while (n-- > 0) {
//                f1 += f2;
//                f2 += f1;
//            }
//            return f2;
//        }
//        return n;
//    }
    /**
     * 输出数字并输出耗时
     **/
    private static void print(int[] array){
        long start = System.nanoTime();
        System.out.print("array = [");
        for (int a = 0; a < array.length; a++) {
            System.out.print(array[a]);
            if (a < array.length - 1) {
                System.out.print(",");
            }
        }
        System.out.print("]");
        System.out.println("    耗时: "+(System.nanoTime() - start));
    }
    
    /**
     * @param args1  计算的组数
     * @param args2 从开始下标位置计算
     * @return 合并两个数组并交叉排序
     **/
    private int[] mergeArray(int[] args1 ,int[] args2){
        //排除参数为空的情况，args1为空 ，直接返回args2，无需计算
        if (args1.length == 0) {
            return args2;
        }
        if (args2.length == 0) {
            return args1;
        }
        int length = args1.length + args2.length;
        int[] newArray = new int[length];

        for (int i = 0; i < length; i++) {
           newArray[i] = findNumInArray(i, args1, args2);
        }
        return newArray;
    }

    /**
     * @param index 取值范围： index >=0 && index < (args1.length +args2.length)
     * @return  返回args1和args2交叉后，下标index对应的数字
     **/
    private int findNumInArray(int index,int[] args1 ,int[] args2){
        int dev = index % 2;  //根据余数判断加入哪一个组数的数据
        int half = index/2;   //计算数据一半，用来判断参数数据是否超过范围
        //从args1中取数据
        if (dev == 0) {
            if (half < args1.length) {
                return args1[index / 2];
            }
            //如果args1数据已经取完了，则必须在args2中取
            return args2[index / 2];
        }
        if (dev == 1) {
            if (half < args2.length) {
                return args2[index / 2];
            }
            return args1[index / 2];
        }
        
        return 0;
    }
    
    /**
     * @param args  计算的组数
     * @param start 从开始下标位置计算
     * @param end 截止计算下标位置
     * @return 递归返回 组数中start到 end数之和
     **/
    private int getSumByRecursion(int[] args,int start,int end){
        if (start > end || (args.length - 1) < end) {
            return 0;
        }
        int sum = args[start];
        if (start == end) {
            return sum;
        }
        int newStart = ++start;
        if (newStart < end) {
            sum += getSumByRecursion(args, start, end);
        } else if (newStart == end) {
            sum += args[newStart];
        }
        return sum;
    }

    /**
     *  对于arr组数的leftIndex下标到rightIndex下标之间的数字快速排序
     * 
     * @param arr  需要排序的组数
     * @param leftIndex 从开始排序的下标位置
     * @param rightIndex 截止排序下标位置
     **/
    public static void quickSort(int[] arr,int leftIndex,int rightIndex){
        int lTempIndex = leftIndex;
        int rTempIndex = rightIndex;
        int temp = 0;
        int f = arr[(leftIndex + rightIndex)/2];        //分界值
        while(lTempIndex < rTempIndex){
            //分界值左边：从lTempIndex下标开始比较当前的数字与分界值大小，如果小于分界值，则下标右移一位（++）。
            while(arr[lTempIndex] < f){
                lTempIndex ++;
            }
            //分界值右边：从rTempIndex下标开始比较当前的数字与分界值大小，如果大于分界值，则下标左移一位（--）。
            while(arr[rTempIndex] > f){
                rTempIndex --;
            }
            //如果分界值左边的数字大于分界值并且分界值右边的数值小于分界值，则将两个数值交换
            if (lTempIndex < rTempIndex) {
                temp = arr[lTempIndex];
                arr[lTempIndex] = arr[rTempIndex];
                arr[rTempIndex] = temp;
                //交换完毕后，左边的数字小于分界值，则左边下标右移一位（++），而右边的数字则会小于分界值，同样下标左移一位（--）
                ++ lTempIndex;
                -- rTempIndex;
            }
        }
        //如果限定的左侧下标小于右侧遍历后的实际下标，则开始递归，对左侧排序。
        if (leftIndex < rTempIndex) {
            quickSort(arr, leftIndex, lTempIndex - 1);
        }
        if (lTempIndex == rTempIndex) {
            lTempIndex ++;
        }
        //如果左侧遍历后实际下标小于限定的右侧下标，则开始递归，对右侧排序。
        if (lTempIndex < rightIndex) {
            quickSort(arr, rTempIndex + 1, rightIndex);
        }
    }
}
